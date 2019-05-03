package ru.spring.localtaxi.service;

import ru.spring.localtaxi.domain.User;

public interface UserService {

    User findById(Long userId);

    User saveUsername(Long userId, String username);

    void savePassword(Long userId, String oldPassword, String newPassword);

    void deleteById(Long userId);

    User saveEmail(Long userId, String email);

    User saveFIO(Long userId, String firstName, String lastName, String middleName);
}
