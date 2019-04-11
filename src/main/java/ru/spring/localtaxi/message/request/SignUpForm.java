package ru.spring.localtaxi.message.request;

import lombok.Data;
import ru.spring.localtaxi.domain.Car;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignUpForm {

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    @Size(max = 60)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 3, max = 50)
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
