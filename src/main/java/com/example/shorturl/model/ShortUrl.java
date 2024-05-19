package com.example.shorturl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Data
public class ShortUrl {
    @Id
    @GeneratedValue
    private long id;

    @Lob
    private String originalUrl;

    private String shortUrl;
    private LocalDateTime createdDate;
    private LocalDateTime expirationDate;
}
