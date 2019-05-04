package ru.spring.localtaxi.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.spring.localtaxi.domain.Car;
import ru.spring.localtaxi.domain.Role;
import ru.spring.localtaxi.domain.User;
import ru.spring.localtaxi.enums.RoleName;
import ru.spring.localtaxi.message.request.LoginForm;
import ru.spring.localtaxi.message.request.SignUpForm;
import ru.spring.localtaxi.message.response.JwtResponse;
import ru.spring.localtaxi.message.response.ResponseMessage;
import ru.spring.localtaxi.repository.RoleRepository;
import ru.spring.localtaxi.repository.UserRepository;
import ru.spring.localtaxi.security.jwt.JwtProvider;
import ru.spring.localtaxi.security.services.UserPrinciple;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

import static ru.spring.localtaxi.enums.RoleName.ROLE_DRIVER;
import static ru.spring.localtaxi.enums.RoleName.ROLE_PASSENGER;

@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final JwtProvider jwtProvider;

    @PostMapping("/api/auth/signin")
    public JwtResponse authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return new JwtResponse(jwt, ((UserPrinciple) userDetails).getUser());
    }

    @PostMapping("/api/auth/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Логин уже занят!"), HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Email уже занят!"), HttpStatus.BAD_REQUEST);
        }

        User user = User.of(
                signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getEmail(),
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getMiddleName(),
                signUpRequest.getCar() == null
                        ? null
                        : Car.of(signUpRequest.getCar().getModel(), signUpRequest.getCar().getRegNumber(), signUpRequest.getCar().getColor())
        );

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "driver":
                    Role driverRole = roleRepository.findByName(ROLE_DRIVER)
                            .orElseThrow(() -> new RuntimeException("Роль \"Водитель\" не найдена"));
                    roles.add(driverRole);
                    break;
                case "passenger":
                    Role passengerRole = roleRepository.findByName(ROLE_PASSENGER)
                            .orElseThrow(() -> new RuntimeException("Роль \"Пассажир\" не найдена"));
                    roles.add(passengerRole);
                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_PASSENGER)
                            .orElseThrow(() -> new RuntimeException("Роль \"Пассажир\" не найдена"));
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);
        userRepository.save(user);
        return new ResponseEntity<>(new ResponseMessage("Вы успешно зарегистрированы!"), HttpStatus.OK);
    }
}