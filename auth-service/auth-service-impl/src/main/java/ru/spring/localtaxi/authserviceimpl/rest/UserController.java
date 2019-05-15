package ru.spring.localtaxi.authserviceimpl.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.spring.localtaxi.authserviceapi.api.UserApi;
import ru.spring.localtaxi.authserviceapi.dto.EmailDTO;
import ru.spring.localtaxi.authserviceapi.dto.FioDTO;
import ru.spring.localtaxi.authserviceapi.dto.PasswordDTO;
import ru.spring.localtaxi.authserviceapi.dto.UserDTO;
import ru.spring.localtaxi.authserviceapi.dto.UsernameDTO;
import ru.spring.localtaxi.authserviceimpl.service.UserService;

@RestController
@AllArgsConstructor
public class UserController implements UserApi {

  private final UserService service;

  @Override
  public UserDTO getCurrentUser() {
    return service.getCurrentUser();
  }

  @Override
  public UserDTO getUserById(@PathVariable Long id) {
    return service.findById(id);
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
}
