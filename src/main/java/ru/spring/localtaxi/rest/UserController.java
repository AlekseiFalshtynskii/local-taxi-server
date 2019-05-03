package ru.spring.localtaxi.rest;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.spring.localtaxi.domain.User;
import ru.spring.localtaxi.message.response.JwtResponse;
import ru.spring.localtaxi.security.jwt.JwtProvider;
import ru.spring.localtaxi.security.services.UserPrinciple;
import ru.spring.localtaxi.service.UserService;

import java.util.Map;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService service;

    private final JwtProvider jwtProvider;

    @GetMapping("/api/users/{userId}")
    public User findById(@PathVariable Long userId) {
        return service.findById(userId);
    }

    @PostMapping("/api/users/{userId}/username")
    public JwtResponse saveUsername(@PathVariable Long userId, @RequestBody Map<String, Object> body) {
        String username = String.valueOf(body.get("username"));
        User user = service.saveUsername(userId, username);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        userPrinciple.setUsername(username);

        String jwt = jwtProvider.generateJwtToken(authentication);

        return new JwtResponse(jwt, user);
    }

    @PostMapping("/api/users/{userId}/password")
    public void savePassword(@PathVariable Long userId, @RequestBody Map<String, Object> body) {
        String oldPassword = String.valueOf(body.get("oldPassword"));
        String newPassword = String.valueOf(body.get("newPassword"));
        service.savePassword(userId, oldPassword, newPassword);
    }

    @PostMapping("/api/users/{userId}/email")
    public User saveEmail(@PathVariable Long userId, @RequestBody Map<String, Object> body) {
        String email = String.valueOf(body.get("email"));
        return service.saveEmail(userId, email);
    }

    @PostMapping("/api/users/{userId}/fio")
    public User saveFIO(@PathVariable Long userId, @RequestBody Map<String, Object> body) {
        String firstName = String.valueOf(body.get("firstName"));
        String lastName = String.valueOf(body.get("lastName"));
        String middleName = String.valueOf(body.get("middleName"));
        return service.saveFIO(userId, firstName, lastName, middleName);
    }

    @DeleteMapping("/api/users/{userId}")
    public void deleteById(@PathVariable Long userId) {
        service.deleteById(userId);
    }
}
