package ru.spring.localtaxi.queueserviceapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import ru.spring.localtaxi.queueserviceapi.api.PlaceInQueueAPI;
import ru.spring.localtaxi.queueserviceapi.config.FeignConfig;

@FeignClient(name = "queue-vf-service", configuration = FeignConfig.class)
public interface PlaceInQueueVFClient extends PlaceInQueueAPI {

}
