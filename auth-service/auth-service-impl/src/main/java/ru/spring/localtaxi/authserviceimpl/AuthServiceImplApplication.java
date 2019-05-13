package ru.spring.localtaxi.authserviceimpl;

import java.util.HashSet;
import java.util.Set;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.spring.localtaxi.authserviceimpl.domain.Authority;
import ru.spring.localtaxi.authserviceimpl.domain.Car;
import ru.spring.localtaxi.authserviceimpl.domain.User;
import ru.spring.localtaxi.authserviceimpl.enums.Authorities;
import ru.spring.localtaxi.authserviceimpl.repository.UserRepository;

@EnableEurekaClient
@SpringBootApplication
public class AuthServiceImplApplication {

  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(AuthServiceImplApplication.class, args);
    PasswordEncoder passwordEncoder = (PasswordEncoder) context.getBean("userPasswordEncoder");
    UserRepository userRepository = context.getBean(UserRepository.class);
    User user = User.of("q", passwordEncoder.encode("1"), "q@q.ru", "Aleksei", null, null, null);
    Set<Authority> authorities = new HashSet<>();
    authorities.add(Authority.of(Authorities.AUTHORITY_PASSENGER));
    user.setAuthorities(authorities);
    Car car = Car.of("model", "regNumber", "color");
    user.setCar(car);
    user = userRepository.save(user);
    System.out.println(user);
  }

}
