package ru.spring.localtaxi.tripserviceimpl.rest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.spring.localtaxi.tripserviceapi.api.TripAPI;
import ru.spring.localtaxi.tripserviceapi.dto.StatisticDTO;
import ru.spring.localtaxi.tripserviceapi.dto.TripDTO;
import ru.spring.localtaxi.tripserviceimpl.service.TripService;

@RestController
@AllArgsConstructor
public class TripController implements TripAPI {

  private final TripService service;

  @HystrixCommand
  @Override
  public TripDTO start() {
    return service.start();
  }

  @HystrixCommand
  @Override
  public TripDTO finish() {
    return service.finish();
  }

  @HystrixCommand
  @Override
  public TripDTO getActive() {
    return service.findActive();
  }

  @HystrixCommand
  @Override
  public List<TripDTO> getAllFinished() {
    return service.findAllFinished();
  }

  @HystrixCommand
  @Override
  public StatisticDTO getStatistic() {
    return service.getStatistic();
  }
}
