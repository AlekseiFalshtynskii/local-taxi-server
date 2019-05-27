package ru.spring.localtaxi.tripserviceapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import ru.spring.localtaxi.tripserviceapi.api.TripAPI;
import ru.spring.localtaxi.tripserviceapi.config.FeignConfig;

@FeignClient(name = "trip-service", configuration = FeignConfig.class)
public interface TripClient extends TripAPI {

}
