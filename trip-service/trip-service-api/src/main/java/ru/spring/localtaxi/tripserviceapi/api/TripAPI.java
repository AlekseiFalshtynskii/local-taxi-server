package ru.spring.localtaxi.tripserviceapi.api;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.spring.localtaxi.tripserviceapi.dto.StatisticDTO;
import ru.spring.localtaxi.tripserviceapi.dto.TripDTO;

public interface TripAPI {

  @PostMapping("/api/trips/start")
  TripDTO start();

  @PostMapping("/api/trips/finish")
  TripDTO finish();

  @GetMapping("/api/trips/active")
  TripDTO getActive();

  @GetMapping("/api/trips/finished")
  List<TripDTO> getAllFinished();

  @GetMapping("/api/trips/statistic")
  StatisticDTO getStatistic();
}
