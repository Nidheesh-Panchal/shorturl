package com.example.shorturl.service;

import com.example.shorturl.model.RedirectUrlResponseDto;
import com.example.shorturl.model.ShortUrl;
import com.example.shorturl.model.ShortUrlRequestDto;
import com.example.shorturl.model.ShortUrlResponseDto;
import com.example.shorturl.repository.ShortUrlRepository;
import com.example.shorturl.utils.HashGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class UrlServiceImpl implements UrlService{

    @Autowired
    HashGenerator hashGenerator;

    @Autowired
    ShortUrlRepository shortUrlRepository;

    @Transactional
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
        shortUrlResponseDto.setCreatedDate(LocalDateTime.now());

        if (shortUrlRequestDto.getExpirationDate() != null){
            shortUrlResponseDto.setExpirationDate(shortUrlRequestDto.getExpirationDate());
        }
        else {
            shortUrlResponseDto.setExpirationDate(LocalDateTime.now().plusDays(3));
        }

        ShortUrl shortUrlRow = new ShortUrl();
        shortUrlRow.setOriginalUrl(shortUrlResponseDto.getOriginalUrl());
        shortUrlRow.setCreatedDate(shortUrlResponseDto.getCreatedDate());
        shortUrlRow.setShortUrl(shortUrlResponseDto.getShortUrl());
        shortUrlRow.setExpirationDate(shortUrlResponseDto.getExpirationDate());
        shortUrlRepository.save(shortUrlRow);

        return shortUrlResponseDto;
    }

    @Transactional
    @Override
    public RedirectUrlResponseDto getOriginalUrl(String shortUrl) {
        RedirectUrlResponseDto redirectUrlResponseDto = new RedirectUrlResponseDto();

        if(!StringUtils.hasText(shortUrl)) {
            redirectUrlResponseDto.setOriginalUrl(null);
            redirectUrlResponseDto.setStatus("400");
            redirectUrlResponseDto.setError("Invalid URL. Please check your short URL");
            return redirectUrlResponseDto;
        }

        ShortUrl shortUrlRow = shortUrlRepository.findByShortUrl(shortUrl);

        if(shortUrlRow == null) {
            redirectUrlResponseDto.setOriginalUrl(null);
            redirectUrlResponseDto.setStatus("400");
            redirectUrlResponseDto.setError("This short URL does not exist on the system. It may have expired.");
            return redirectUrlResponseDto;
        }

        if(shortUrlRow.getExpirationDate().isBefore(LocalDateTime.now())) {
            redirectUrlResponseDto.setOriginalUrl(null);
            redirectUrlResponseDto.setStatus("400");
            redirectUrlResponseDto.setError("This short URL has expired. Generate a new short URL.");
            return redirectUrlResponseDto;
        }

        redirectUrlResponseDto.setOriginalUrl(shortUrlRow.getOriginalUrl());
        redirectUrlResponseDto.setStatus("200");
        redirectUrlResponseDto.setError(null);
        return redirectUrlResponseDto;

    }
}
