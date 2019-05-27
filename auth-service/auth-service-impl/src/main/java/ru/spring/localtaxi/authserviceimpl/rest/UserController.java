package ru.spring.localtaxi.authserviceimpl.rest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.spring.localtaxi.authserviceapi.api.UserAPI;
import ru.spring.localtaxi.authserviceapi.dto.EmailDTO;
import ru.spring.localtaxi.authserviceapi.dto.FioDTO;
import ru.spring.localtaxi.authserviceapi.dto.PasswordDTO;
import ru.spring.localtaxi.authserviceapi.dto.UserDTO;
import ru.spring.localtaxi.authserviceapi.dto.UsernameDTO;
import ru.spring.localtaxi.authserviceimpl.service.UserService;

@RestController
@AllArgsConstructor
public class UserController implements UserAPI {

  private final UserService service;

  @HystrixCommand
  @Override
  public UserDTO getCurrentUser() {
    return service.getCurrentUser();
  }

  @HystrixCommand
  @Override
  public UserDTO getUserById(@PathVariable Long id) {
    return service.findById(id);
  }

  @HystrixCommand
  @Override
  public void saveUsername(UsernameDTO dto) {
    service.saveUsername(dto.getUsername());
  }

  @HystrixCommand
  @Override
  public void savePassword(PasswordDTO dto) {
    service.savePassword(dto.getOldPassword(), dto.getNewPassword());
  }

  @HystrixCommand
  @Override
  public void saveEmail(EmailDTO dto) {
    service.saveEmail(dto.getEmail());
  }

  @HystrixCommand
  @Override
  public void saveFIO(FioDTO dto) {
    service.saveFIO(dto.getFirstName(), dto.getLastName(), dto.getMiddleName());
  }

  @HystrixCommand
  @Override
  public void deleteById() {
    service.deleteUser();
  }
}
