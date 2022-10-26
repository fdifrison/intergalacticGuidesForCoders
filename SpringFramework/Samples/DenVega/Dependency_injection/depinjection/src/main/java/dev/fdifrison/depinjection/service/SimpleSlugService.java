package dev.fdifrison.depinjection.service;

import org.springframework.stereotype.Service;

@Service
public class SimpleSlugService implements SlugService {

    @Override
    public String slugify(String input) {
        return input.
                toLowerCase()
                .replaceAll("[^a-zA-Z0-9 ]", "") // replace all special char except for spaces
                .replaceAll(" ", "-"); // replace spaces with hyphens
    }
}
