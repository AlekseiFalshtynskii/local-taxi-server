package ru.spring.localtaxi.authserviceapi.api;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.spring.localtaxi.authserviceapi.dto.SignUpDTO;

public interface AuthApi {

  @PostMapping("/api/auth/signup")
  ResponseEntity<String> signup(@Valid @RequestBody SignUpDTO signUpRequest);
}
