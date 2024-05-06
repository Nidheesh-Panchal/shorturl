package com.example.shorturl.utils;

import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class HashGenerator{

    public String createHash(String url) {
        // TODO: add hash generator code
        LocalDateTime localDateTime = LocalDateTime.now();
        String shortUrl = Hashing.murmur3_32()
                .hashString(url.concat(localDateTime.toString()), StandardCharsets.UTF_8)
                .toString();
        return shortUrl;
    }
}