package ru.spring.localtaxi.queueserviceapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import ru.spring.localtaxi.queueserviceapi.api.PlaceInQueueApi;
import ru.spring.localtaxi.queueserviceapi.config.FeignConfig;

@FeignClient(name = "queue-fv-service", configuration = FeignConfig.class)
public interface PlaceInQueueFVClient extends PlaceInQueueApi {

}
