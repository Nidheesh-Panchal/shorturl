package com.example.shorturl.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ShortUrlResponseDto {
    private String originalUrl;
    private String shortUrl;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private ArrayList<String> errors;
}
