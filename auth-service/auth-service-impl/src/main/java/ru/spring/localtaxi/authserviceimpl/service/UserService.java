package ru.spring.localtaxi.authserviceimpl.service;

import ru.spring.localtaxi.authserviceapi.dto.UserDTO;
import ru.spring.localtaxi.authserviceimpl.domain.User;

public interface UserService {

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);

  UserDTO getCurrentUser();

  UserDTO findById(Long userId);

  void save(User user);

  void saveUsername(String username);

  void savePassword(String oldPassword, String newPassword);

  void deleteUser();

  void saveEmail(String email);

  void saveFIO(String firstName, String lastName, String middleName);
}
