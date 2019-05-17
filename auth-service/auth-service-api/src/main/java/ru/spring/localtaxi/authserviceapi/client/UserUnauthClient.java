package ru.spring.localtaxi.authserviceapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import ru.spring.localtaxi.authserviceapi.api.UserUnauthApi;

@FeignClient(name = "auth-service", contextId = "user-unauth-client")
public interface UserUnauthClient extends UserUnauthApi {

}
