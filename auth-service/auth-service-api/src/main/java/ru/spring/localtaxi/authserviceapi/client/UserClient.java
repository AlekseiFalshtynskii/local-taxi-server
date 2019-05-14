package ru.spring.localtaxi.authserviceapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import ru.spring.localtaxi.authserviceapi.api.UserApi;

@FeignClient("auth-service")
public interface UserClient extends UserApi {

}
