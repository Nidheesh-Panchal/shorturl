package com.example.shorturl.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class ShortUrlRequestDto {
    private @NonNull String originalUrl;
    private LocalDateTime expirationDate;
}
