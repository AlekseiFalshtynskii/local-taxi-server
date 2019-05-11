package ru.spring.localtaxi.authserviceapi.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("auth-service")
public interface UserClient {

  String GET_CURRENT_USER_PATH = "/api/users";

  @GetMapping(GET_CURRENT_USER_PATH)
  String getCurrentUser();
}
