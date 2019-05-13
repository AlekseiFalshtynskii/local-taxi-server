package ru.spring.localtaxi.authserviceimpl.rest;

import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.spring.localtaxi.authserviceapi.api.UserClient;
import ru.spring.localtaxi.authserviceapi.dto.AuthorityDTO;
import ru.spring.localtaxi.authserviceapi.dto.CarDTO;
import ru.spring.localtaxi.authserviceapi.dto.EmailDTO;
import ru.spring.localtaxi.authserviceapi.dto.FioDTO;
import ru.spring.localtaxi.authserviceapi.dto.PasswordDTO;
import ru.spring.localtaxi.authserviceapi.dto.UserDTO;
import ru.spring.localtaxi.authserviceapi.dto.UsernameDTO;
import ru.spring.localtaxi.authserviceimpl.domain.User;
import ru.spring.localtaxi.authserviceimpl.service.UserService;

@RestController
@AllArgsConstructor
public class UserController implements UserClient {

  private final UserService service;

  @Override
  public UserDTO getCurrentUser() {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return this.fromUser(user);
  }

  @Override
  public UserDTO getUserById(@PathVariable Long id) {
    return this.fromUser(service.findById(id));
  }

  @Override
  public void saveUsername(UsernameDTO dto) {
    service.saveUsername(dto.getUsername());
  }

  @Override
  public void savePassword(PasswordDTO dto) {
    service.savePassword(dto.getOldPassword(), dto.getNewPassword());
  }

  @Override
  public void saveEmail(EmailDTO dto) {
    service.saveEmail(dto.getEmail());
  }

  @Override
  public void saveFIO(FioDTO dto) {
    service.saveFIO(dto.getFirstName(), dto.getLastName(), dto.getMiddleName());
  }

  @Override
  public void deleteById() {
    service.deleteUser();
  }

  private UserDTO fromUser(User user) {
    if (user == null) {
      return null;
    }
    Set<AuthorityDTO> authorities = user.getAuthorities().stream()
        .map(authority -> AuthorityDTO.of(authority.getId(), authority.getAuthority()))
        .collect(Collectors.toSet());
    CarDTO car = user.getCar() == null ? null : CarDTO
        .of(user.getCar().getId(), user.getCar().getModel(), user.getCar().getRegNumber(),
            user.getCar().getColor());
    return UserDTO
        .of(user.getId(), user.getUsername(), user.getPassword(), user.isEnabled(), authorities,
            user.getEmail(), user.getFirstName(), user.getLastName(),
            user.getMiddleName(), car);
  }
}
