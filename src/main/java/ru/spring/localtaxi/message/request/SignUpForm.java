package ru.spring.localtaxi.message.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class SignUpForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Email
    private String email;

    private Set<String> roles;

    @NotBlank
    private String firstName;

    private String lastName;

    private String middleName;

    private Car car;

    @Data
    public class Car {
        private String model;

        private String regNumber;

        private String color;
    }
}
