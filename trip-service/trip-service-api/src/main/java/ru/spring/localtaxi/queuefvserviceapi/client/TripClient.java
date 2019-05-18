package ru.spring.localtaxi.queuefvserviceapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import ru.spring.localtaxi.queuefvserviceapi.api.TripApi;
import ru.spring.localtaxi.queuefvserviceapi.config.FeignConfig;

@FeignClient(name = "trip-service", configuration = FeignConfig.class)
public interface TripClient extends TripApi {

}
