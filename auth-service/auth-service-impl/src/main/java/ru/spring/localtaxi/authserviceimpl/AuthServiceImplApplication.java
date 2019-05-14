package ru.spring.localtaxi.authserviceimpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AuthServiceImplApplication {

  public static void main(String[] args) {
    SpringApplication.run(AuthServiceImplApplication.class, args);
  }

}
