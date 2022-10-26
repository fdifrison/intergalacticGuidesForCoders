package dev.fdifrison.blog.model;

import org.springframework.data.annotation.Id;

public record Author(
        @Id
        Integer id,
        String firstName,
        String lastName,
        String email,
        String username
) {
}
