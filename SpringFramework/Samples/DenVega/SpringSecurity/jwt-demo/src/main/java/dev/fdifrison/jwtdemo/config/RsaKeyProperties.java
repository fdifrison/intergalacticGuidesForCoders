package dev.fdifrison.jwtdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/*
 the configuration properties is used to externalize the configuration properties, in this case public and private
 key; we will define the rsa.public-key in the application.yml file
*/
@ConfigurationProperties(prefix = "rsa")
public record RsaKeyProperties(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
}
