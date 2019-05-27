package ru.spring.localtaxi.authserviceapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import ru.spring.localtaxi.authserviceapi.api.AuthAPI;
import ru.spring.localtaxi.authserviceapi.config.FeignConfig;

@FeignClient(name = "auth-service", contextId = "auth-client", configuration = FeignConfig.class)
public interface AuthClient extends AuthAPI {

}
