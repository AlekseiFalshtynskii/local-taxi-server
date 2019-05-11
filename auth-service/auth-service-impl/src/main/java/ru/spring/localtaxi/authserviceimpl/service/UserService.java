package ru.spring.localtaxi.authserviceimpl.service;

import ru.spring.localtaxi.authserviceimpl.domain.User;

public interface UserService {

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);

  User findById(Long userId);

  User save(User user);

  User saveUsername(Long userId, String username);

  void savePassword(Long userId, String oldPassword, String newPassword);

  void deleteById(Long userId);

  User saveEmail(Long userId, String email);

  User saveFIO(Long userId, String firstName, String lastName, String middleName);
}
