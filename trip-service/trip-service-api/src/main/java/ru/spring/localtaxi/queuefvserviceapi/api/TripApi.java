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

  @GetMapping("/api/trips/active")
  TripDTO getActive();

  @GetMapping("/api/trips/finished")
  List<TripDTO> getAllFinished();
}
