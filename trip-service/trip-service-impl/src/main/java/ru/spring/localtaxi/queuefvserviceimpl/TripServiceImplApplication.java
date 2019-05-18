package ru.spring.localtaxi.queuefvserviceimpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = "ru.spring.localtaxi")
@SpringBootApplication
public class TripServiceImplApplication {

  public static void main(String[] args) {
    SpringApplication.run(TripServiceImplApplication.class, args);
  }

}
