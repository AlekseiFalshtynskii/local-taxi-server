package ru.spring.localtaxi.authserviceimpl.rest;

import java.security.Principal;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.spring.localtaxi.authserviceimpl.domain.User;
import ru.spring.localtaxi.authserviceimpl.service.UserService;

@RestController
@AllArgsConstructor
public class UserController {

  private final UserService service;

  @GetMapping("/api/users")
  public User getCurrentUser(Principal principal) {
    return (User) ((OAuth2Authentication) principal).getPrincipal();
  }

  @GetMapping("/api/users/{id}")
  public User getCurrentUser(@PathVariable Long id) {
    return service.findById(id);
  }
}
