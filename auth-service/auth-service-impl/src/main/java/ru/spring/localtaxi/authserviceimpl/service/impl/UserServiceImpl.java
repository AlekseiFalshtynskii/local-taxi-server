package ru.spring.localtaxi.authserviceimpl.service.impl;

import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.spring.localtaxi.authserviceapi.dto.AuthorityDTO;
import ru.spring.localtaxi.authserviceapi.dto.CarDTO;
import ru.spring.localtaxi.authserviceapi.dto.UserDTO;
import ru.spring.localtaxi.authserviceimpl.domain.User;
import ru.spring.localtaxi.authserviceimpl.repository.UserRepository;
import ru.spring.localtaxi.authserviceimpl.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  private final PasswordEncoder userPasswordEncoder;

  private User currentUser() {
    return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

  @Override
  public UserDTO getCurrentUser() {
    return fromEntity(currentUser());
  }

  @Override
  public boolean existsByUsername(String username) {
    return repository.existsByUsername(username);
  }

  @Override
  public boolean existsByEmail(String email) {
    return repository.existsByEmail(email);
  }

  @Override
  public UserDTO findById(Long userId) {
    return fromEntity(repository.findById(userId).orElse(null));
  }

  @Override
  public void save(User user) {
    repository.save(user);
  }

  @Override
  public void saveUsername(String username) {
    if (existsByUsername(username)) {
      throw new ValidationException("Логин уже занят!");
    }
    User user = currentUser();
    user.setUsername(username);
    repository.save(user);
  }

  @Override
  public void savePassword(String oldPassword, String newPassword) {
    User user = currentUser();
    if (!userPasswordEncoder.matches(oldPassword, user.getPassword())) {
      throw new ValidationException("Пароль указан неверно!");
    }
    user.setPassword(userPasswordEncoder.encode(newPassword));
    repository.save(user);
  }

  @Override
  public void saveEmail(String email) {
    if (existsByEmail(email)) {
      throw new ValidationException("Email уже занят!");
    }
    User user = currentUser();
    user.setEmail(email);
    repository.save(user);
  }

  @Override
  public void saveFIO(String firstName, String lastName, String middleName) {
    User user = currentUser();
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setMiddleName(middleName);
    repository.save(user);
  }

  @Override
  public void deleteUser() {
    repository.deleteById(getCurrentUser().getId());
  }

  private UserDTO fromEntity(User user) {
    if (user != null) {
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
    return null;
  }
}
