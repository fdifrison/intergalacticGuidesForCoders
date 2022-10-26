package dev.fdifrison.blog.model.dto;

import dev.fdifrison.blog.model.Author;
import dev.fdifrison.blog.model.Post;

public record PostDetails(Post post, Author author) {
}
