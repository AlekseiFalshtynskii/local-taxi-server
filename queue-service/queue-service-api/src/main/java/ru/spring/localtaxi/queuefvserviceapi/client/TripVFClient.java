package ru.spring.localtaxi.queuefvserviceapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import ru.spring.localtaxi.queuefvserviceapi.api.TripApi;
import ru.spring.localtaxi.queuefvserviceapi.config.FeignConfiguration;

@FeignClient(name = "queue-vf-service", configuration = FeignConfiguration.class)
public interface TripVFClient extends TripApi {

}
