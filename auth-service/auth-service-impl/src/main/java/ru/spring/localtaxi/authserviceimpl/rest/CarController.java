package ru.spring.localtaxi.authserviceimpl.rest;

import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.spring.localtaxi.authserviceimpl.domain.Car;
import ru.spring.localtaxi.authserviceimpl.service.CarService;

@RestController
@AllArgsConstructor
public class CarController {

  private final CarService service;

  @PostMapping("/api/cars")
  public void saveCar(@Valid @RequestBody Car car) {
    service.save(car);
  }
}
