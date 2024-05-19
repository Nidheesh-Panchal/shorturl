package com.example.shorturl.repository;

import com.example.shorturl.model.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl,Long> {
    ShortUrl findByShortUrl(String shortUrl);
}
