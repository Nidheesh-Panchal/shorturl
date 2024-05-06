package com.example.shorturl.controller;

import com.example.shorturl.model.RedirectUrlRequestDto;
import com.example.shorturl.model.RedirectUrlResponseDto;
import com.example.shorturl.model.ShortUrlRequestDto;
import com.example.shorturl.model.ShortUrlResponseDto;
import com.example.shorturl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        RedirectUrlResponseDto redirectUrlResponseDto = new RedirectUrlResponseDto();
        return new ResponseEntity<>(redirectUrlResponseDto, HttpStatus.OK);
    }
}
