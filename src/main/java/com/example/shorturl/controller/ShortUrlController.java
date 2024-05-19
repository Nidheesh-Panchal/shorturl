package com.example.shorturl.controller;

import com.example.shorturl.model.*;
import com.example.shorturl.repository.ShortUrlRepository;
import com.example.shorturl.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
public class ShortUrlController {

    @Autowired
    UrlService urlService;

    @PostMapping("/generateShortUrl")
    public ResponseEntity<ShortUrlResponseDto> generateShortUrl(@RequestBody ShortUrlRequestDto shortUrlRequestDto){
        ShortUrlResponseDto shortUrlResponseDto = urlService.generateShortUrl(shortUrlRequestDto);

        return new ResponseEntity<>(shortUrlResponseDto, HttpStatus.OK);
    }

    @PostMapping("/redirect")
    public ResponseEntity<RedirectUrlResponseDto> getOriginalUrl(@RequestBody RedirectUrlRequestDto redirectUrlRequestDto){
        RedirectUrlResponseDto redirectUrlResponseDto = urlService.getOriginalUrl(redirectUrlRequestDto.getShortUrl());
        return new ResponseEntity<>(redirectUrlResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<RedirectUrlResponseDto> redirect(@PathVariable String shortUrl, HttpServletResponse httpServletResponse){

        RedirectUrlResponseDto redirectUrlResponseDto = urlService.getOriginalUrl(shortUrl);

        if(redirectUrlResponseDto.getError() != null) {
            return new ResponseEntity<>(redirectUrlResponseDto, HttpStatus.OK);
        }

        try {
            httpServletResponse.sendRedirect(redirectUrlResponseDto.getOriginalUrl());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
