package ru.spring.localtaxi.authserviceapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import ru.spring.localtaxi.authserviceapi.api.UserApi;
import ru.spring.localtaxi.authserviceapi.config.FeignConfig;

@FeignClient(name = "auth-service", contextId = "user-client", configuration = FeignConfig.class)
public interface UserClient extends UserApi {

}
