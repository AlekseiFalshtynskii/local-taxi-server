package ru.spring.localtaxi.queuefvserviceapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import ru.spring.localtaxi.queuefvserviceapi.api.PlaceInQueueApi;
import ru.spring.localtaxi.queuefvserviceapi.config.FeignConfig;

@FeignClient(name = "queue-vf-service", configuration = FeignConfig.class)
public interface PlaceInQueueVFClient extends PlaceInQueueApi {

}
