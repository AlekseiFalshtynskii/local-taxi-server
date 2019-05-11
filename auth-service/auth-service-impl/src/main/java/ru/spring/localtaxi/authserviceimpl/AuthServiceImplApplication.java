package ru.spring.localtaxi.authserviceimpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableAuthorizationServer
@EnableResourceServer
@SpringBootApplication
public class AuthServiceImplApplication {

  public static void main(String[] args) {
    SpringApplication.run(AuthServiceImplApplication.class, args);
  }

}
