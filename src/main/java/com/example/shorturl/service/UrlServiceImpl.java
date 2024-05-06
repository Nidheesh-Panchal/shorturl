package com.example.shorturl.service;

import com.example.shorturl.model.ShortUrlRequestDto;
import com.example.shorturl.model.ShortUrlResponseDto;
import com.example.shorturl.utils.HashGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class UrlServiceImpl implements UrlService{

    @Autowired
    HashGenerator hashGenerator;

    @Override
    public ShortUrlResponseDto generateShortUrl(ShortUrlRequestDto shortUrlRequestDto) {
        ShortUrlResponseDto shortUrlResponseDto = new ShortUrlResponseDto();
        shortUrlResponseDto.setOriginalUrl(shortUrlRequestDto.getOriginalUrl());

        if(!StringUtils.hasText(shortUrlRequestDto.getOriginalUrl())){

            ArrayList<String> errors = new ArrayList<>();
            errors.add("Original URL entered has no text to generate short URL for. Please check your input URL.");
            shortUrlResponseDto.setErrors(errors);
            return shortUrlResponseDto;
        }

        String shortUrl = hashGenerator.createHash(shortUrlRequestDto.getOriginalUrl());
        shortUrlResponseDto.setShortUrl(shortUrl);
        shortUrlResponseDto.setCreatedAt(LocalDateTime.now());
        // TODO: Add proper logic for expiration date.
        if (shortUrlRequestDto.getExpirationDate() != null){
            shortUrlResponseDto.setExpiresAt(shortUrlRequestDto.getExpirationDate());
        }
        else {
            shortUrlResponseDto.setExpiresAt(LocalDateTime.now().plusDays(3));
        }
        return shortUrlResponseDto;
    }

    @Override
    public void getOriginalUrl() {

    }
}
