package ru.spring.localtaxi.queuefvserviceimpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import ru.spring.localtaxi.authserviceapi.client.UserClient;

@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = UserClient.class)
@SpringBootApplication
public class QueueServiceImplApplication {

  public static void main(String[] args) {
    SpringApplication.run(QueueServiceImplApplication.class, args);
  }

}
