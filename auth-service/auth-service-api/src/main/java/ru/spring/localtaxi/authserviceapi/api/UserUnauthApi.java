package ru.spring.localtaxi.authserviceapi.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.spring.localtaxi.authserviceapi.dto.UserDTO;

public interface UserUnauthApi {

  @GetMapping("/api/users/{id}")
  UserDTO getUserById(@PathVariable(name = "id") Long id);
}
