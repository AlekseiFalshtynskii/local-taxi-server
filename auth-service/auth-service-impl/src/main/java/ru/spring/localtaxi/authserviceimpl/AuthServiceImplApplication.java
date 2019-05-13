package ru.spring.localtaxi.authserviceimpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AuthServiceImplApplication {

  public static void main(String[] args) {
    SpringApplication.run(AuthServiceImplApplication.class, args);
  }

}
