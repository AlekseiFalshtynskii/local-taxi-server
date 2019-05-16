package ru.spring.localtaxi.queuefvserviceimpl.rest;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.spring.localtaxi.queuefvserviceapi.api.TripApi;
import ru.spring.localtaxi.queuefvserviceapi.dto.TripDTO;
import ru.spring.localtaxi.queuefvserviceimpl.service.TripService;

@RestController
@AllArgsConstructor
public class TripController implements TripApi {

  private final TripService service;

  @Override
  public TripDTO start() {
    return service.start();
  }

  @Override
  public TripDTO finish() {
    return service.finish();
  }

  @Override
  public TripDTO findByDriverActive() {
    return service.findByDriverActive();
  }

  @Override
  public TripDTO findByPassengerActive() {
    return service.findByPassengerActive();
  }

  @Override
  public List<TripDTO> findByDriver() {
    return service.findByDriver();
  }

  @Override
  public List<TripDTO> findByPassenger() {
    return service.findByPassenger();
  }
}
