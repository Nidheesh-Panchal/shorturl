package com.example.shorturl.controller;

import com.example.shorturl.model.RedirectUrlResponseDto;
import com.example.shorturl.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RedirectController {

    @Autowired
    UrlService urlService;

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
