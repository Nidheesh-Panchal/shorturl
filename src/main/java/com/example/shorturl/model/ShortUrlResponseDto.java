package com.example.shorturl.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@NoArgsConstructor
public class ShortUrlResponseDto {
    private String originalUrl;
    private String shortUrl;
    private LocalDateTime createdDate;
    private LocalDateTime expirationDate;
    private ArrayList<String> errors;
}
