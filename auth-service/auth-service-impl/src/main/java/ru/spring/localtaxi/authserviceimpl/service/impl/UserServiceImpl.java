package ru.spring.localtaxi.authserviceimpl.service.impl;

import javax.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.spring.localtaxi.authserviceimpl.domain.User;
import ru.spring.localtaxi.authserviceimpl.repository.UserRepository;
import ru.spring.localtaxi.authserviceimpl.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  private final PasswordEncoder userPasswordEncoder;

  private User getCurrentUser() {
    return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
  public User findById(Long userId) {
    return repository.findById(userId).orElse(null);
  }

  @Override
  public User save(User user) {
    return repository.save(user);
  }

  @Override
  public void saveUsername(String username) {
    if (existsByUsername(username)) {
      throw new ValidationException("Логин уже занят!");
    }
    User user = getCurrentUser();
    user.setUsername(username);
    save(user);
  }

  @Override
  public void savePassword(String oldPassword, String newPassword) {
    User user = getCurrentUser();
    if (!userPasswordEncoder.matches(oldPassword, user.getPassword())) {
      throw new ValidationException("Пароль указан неверно!");
    }
    user.setPassword(userPasswordEncoder.encode(newPassword));
    save(user);
  }

  @Override
  public void saveEmail(String email) {
    if (existsByEmail(email)) {
      throw new ValidationException("Email уже занят!");
    }
    User user = getCurrentUser();
    user.setEmail(email);
    save(user);
  }

  @Override
  public void saveFIO(String firstName, String lastName, String middleName) {
    User user = getCurrentUser();
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setMiddleName(middleName);
    save(user);
  }

  @Override
  public void deleteUser() {
    repository.deleteById(getCurrentUser().getId());
  }
}
