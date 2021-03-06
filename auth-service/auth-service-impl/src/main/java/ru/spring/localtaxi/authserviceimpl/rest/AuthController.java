package ru.spring.localtaxi.authserviceimpl.rest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import ru.spring.localtaxi.authserviceapi.api.AuthAPI;
import ru.spring.localtaxi.authserviceapi.dto.SignUpDTO;
import ru.spring.localtaxi.authserviceapi.enums.Authorities;
import ru.spring.localtaxi.authserviceimpl.domain.Authority;
import ru.spring.localtaxi.authserviceimpl.domain.Car;
import ru.spring.localtaxi.authserviceimpl.domain.User;
import ru.spring.localtaxi.authserviceimpl.service.UserService;

@RestController
@AllArgsConstructor
public class AuthController implements AuthAPI {

  private final UserService service;

  private final PasswordEncoder userPasswordEncoder;

  @HystrixCommand
  @Override
  public ResponseEntity<String> signup(SignUpDTO signUpRequest) {
    if (service.existsByUsername(signUpRequest.getUsername())) {
      return new ResponseEntity<>("Логин уже занят!", HttpStatus.BAD_REQUEST);
    }

    if (service.existsByEmail(signUpRequest.getEmail())) {
      return new ResponseEntity<>("Email уже занят!", HttpStatus.BAD_REQUEST);
    }

    User user = User.of(
        signUpRequest.getUsername(),
        userPasswordEncoder.encode(signUpRequest.getPassword()),
        signUpRequest.getEmail(),
        signUpRequest.getFirstName(),
        signUpRequest.getLastName(),
        signUpRequest.getMiddleName(),
        signUpRequest.getCar() == null
            ? null
            : Car.of(signUpRequest.getCar().getModel(), signUpRequest.getCar().getRegNumber(),
                signUpRequest.getCar().getColor())
    );
    Set<Authority> authorities = signUpRequest.getRoles()
        .stream()
        .map(Authorities::valueOf)
        .map(Authority::of)
        .collect(Collectors.toSet());

    user.setAuthorities(authorities);
    service.save(user);
    return ResponseEntity.ok().build();
  }
}
