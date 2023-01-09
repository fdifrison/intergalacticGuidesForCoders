package dev.fdifrison.jwtdemo.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class TokenService {

    private final JwtEncoder jwtEncoder;

    public TokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

}


/*
Here's a step-by-step breakdown of the generateToken method:
The method starts by getting the current time using Instant.now().
It then creates a string called scope that represents the authorities granted to the subject of the token.
It does this by using the getAuthorities() method of the Authentication object to get a collection of GrantedAuthority objects,
which represent the authorities granted to the subject. It then uses a stream to map each GrantedAuthority object to its getAuthority() method,
which returns the authority as a string. Finally, it uses the collect method to join all of the strings into a single string, separated by a space character.

Next, it creates a JwtClaimsSet object using the JwtClaimsSet.builder() method. This object is a builder for creating a set of claims for a JWT.
It uses the issuer method to set the issuer of the token to "self", the issuedAt method to set the time that the token was issued to the current time,
the expiresAt method to set the expiration time of the token to one hour after the current time,
the subject method to set the subject of the token to the name of the subject in the Authentication object,
and the claim method to set the scope claim to the scope string that was created earlier. Finally, it calls the build method to create the JwtClaimsSet object.

It then creates a JwtEncoderParameters object using the JwtEncoderParameters.from method and passes it the JwtClaimsSet object as an argument.
This object is used to specify the parameters for encoding a JWT.

It calls the encode method of the jwtEncoder object and passes it the JwtEncoderParameters object as an argument.
This method encodes the claims in the JwtClaimsSet object into a JWT and returns a JwtToken object, which represents the JWT.

Finally, it returns the token value of the JwtToken object as a String using the getTokenValue method.
This is the actual JWT as a string, which can be transmitted to the client and used to authenticate requests.
*/
