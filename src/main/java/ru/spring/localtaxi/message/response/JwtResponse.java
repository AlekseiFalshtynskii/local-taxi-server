package ru.spring.localtaxi.message.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.spring.localtaxi.domain.User;

@Data
@AllArgsConstructor
public class JwtResponse {

    private final String type = "Bearer";

    private String accessToken;

    private User user;
}
