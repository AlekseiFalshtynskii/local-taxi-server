package ru.spring.localtaxi.queuefvserviceapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import ru.spring.localtaxi.queuefvserviceapi.api.PlaceInQueueApi;
import ru.spring.localtaxi.queuefvserviceapi.config.FeignConfiguration;

@FeignClient(name = "queue-fv-service", configuration = FeignConfiguration.class)
public interface PlaceInQueueFVClient extends PlaceInQueueApi {

}
