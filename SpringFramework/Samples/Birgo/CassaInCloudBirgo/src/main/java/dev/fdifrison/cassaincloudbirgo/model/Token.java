package dev.fdifrison.cassaincloudbirgo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@JsonDeserialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class Token {

    private String accessToken;
    private int expiresIn;
    private String tokenType;

}
