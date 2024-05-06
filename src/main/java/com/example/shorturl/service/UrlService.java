package com.example.shorturl.service;

import com.example.shorturl.model.ShortUrlRequestDto;
import com.example.shorturl.model.ShortUrlResponseDto;

public interface UrlService {
    ShortUrlResponseDto generateShortUrl(ShortUrlRequestDto shortUrlRequestDto);
    void getOriginalUrl();
}
