package ru.spring.localtaxi.queuefvserviceapi.api;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.spring.localtaxi.queuefvserviceapi.dto.TripDTO;

public interface TripApi {

  @PostMapping("/api/trips/start")
  TripDTO start();

  @PostMapping("/api/trips/finish")
  TripDTO finish();

  @GetMapping("/api/trips/driver/active")
  TripDTO findByDriverActive();

  @GetMapping("/api/trips/passenger/active")
  TripDTO findByPassengerActive();

  @GetMapping("/api/trips/driver/finished")
  List<TripDTO> findByDriver();

  @GetMapping("/api/trips/passenger/finished")
  List<TripDTO> findByPassenger();
}
