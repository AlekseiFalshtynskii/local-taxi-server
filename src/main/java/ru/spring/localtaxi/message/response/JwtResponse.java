package ru.spring.localtaxi.message.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@AllArgsConstructor
public class JwtResponse {

    private final String type = "Bearer";

    private String accessToken;

    private String username;

    private Collection<? extends GrantedAuthority> authorities;
}
