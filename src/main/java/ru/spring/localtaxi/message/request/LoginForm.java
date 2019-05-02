package ru.spring.localtaxi.message.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
