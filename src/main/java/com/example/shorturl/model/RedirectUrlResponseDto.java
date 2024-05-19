package com.example.shorturl.model;

import lombok.*;

@Data
@NoArgsConstructor
public class RedirectUrlResponseDto {
    private String originalUrl;
    private String error;
    private String status;
}
