package dev.fdifrison.depinjection.model;

import java.time.LocalDateTime;


public record Article(
        Integer id,
        String title,
        String slug, // a slug is a placeholder for identifier like "id" that we don't want to display to the user
        String content,
        LocalDateTime publishedOn
) {
}
