package ru.spring.localtaxi.authserviceapi.api;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.spring.localtaxi.authserviceapi.dto.EmailDTO;
import ru.spring.localtaxi.authserviceapi.dto.FioDTO;
import ru.spring.localtaxi.authserviceapi.dto.PasswordDTO;
import ru.spring.localtaxi.authserviceapi.dto.UserDTO;
import ru.spring.localtaxi.authserviceapi.dto.UsernameDTO;

public interface UserApi {

  @GetMapping("/api/users")
  UserDTO getCurrentUser();

  @GetMapping("/api/users/{id}")
  UserDTO getUserById(@PathVariable(name = "id") Long id);

  @PostMapping("/api/users/username")
  void saveUsername(@Valid @RequestBody UsernameDTO dto);

  @PostMapping("/api/users/password")
  void savePassword(@Valid @RequestBody PasswordDTO dto);

  @PostMapping("/api/users/email")
  void saveEmail(@Valid @RequestBody EmailDTO dto);

  @PostMapping("/api/users/fio")
  void saveFIO(@Valid @RequestBody FioDTO dto);

  @DeleteMapping("/api/users")
  void deleteById();
}
