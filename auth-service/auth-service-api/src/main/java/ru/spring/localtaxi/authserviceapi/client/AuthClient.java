package ru.spring.localtaxi.authserviceapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import ru.spring.localtaxi.authserviceapi.api.AuthApi;
import ru.spring.localtaxi.authserviceapi.config.FeignConfiguration;

@FeignClient(name = "auth-service1", configuration = FeignConfiguration.class)
public interface AuthClient extends AuthApi {

}
