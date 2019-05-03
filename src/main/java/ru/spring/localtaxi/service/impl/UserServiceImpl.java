package ru.spring.localtaxi.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.spring.localtaxi.domain.User;
import ru.spring.localtaxi.repository.UserRepository;
import ru.spring.localtaxi.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User findById(Long userId) {
        return repository.findById(userId).orElseThrow(
                () -> new RuntimeException("Пользователь не найден!")
        );
    }

    @Override
    public User saveUsername(Long userId, String username) {
        User user = repository.findByUsername(username).orElse(null);
        if (user != null) {
            throw new RuntimeException("Логин уже занят!");
        }
        user = repository.findById(userId).orElseThrow(
                () -> new RuntimeException("Пользователь не найден!")
        );
        user.setUsername(username);
        return repository.save(user);
    }

    @Override
    public void savePassword(Long userId, String oldPassword, String newPassword) {
        User user = repository.findById(userId).orElseThrow(
                () -> new RuntimeException("Пользователь не найден!")
        );
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("Пароль указан неверно!");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        repository.save(user);
    }

    @Override
    public User saveEmail(Long userId, String email) {
        User user = repository.findByEmail(email).orElse(null);
        if (user != null) {
            throw new RuntimeException("Email уже занят!");
        }
        user = repository.findById(userId).orElseThrow(
                () -> new RuntimeException("Пользователь не найден!")
        );
        user.setEmail(email);
        return repository.save(user);
    }

    @Override
    public User saveFIO(Long userId, String firstName, String lastName, String middleName) {
        User user = repository.findById(userId).orElseThrow(
                () -> new RuntimeException("Пользователь не найден!")
        );
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setMiddleName(middleName);
        return repository.save(user);
    }

    @Override
    public void deleteById(Long userId) {
        repository.deleteById(userId);
    }
}
