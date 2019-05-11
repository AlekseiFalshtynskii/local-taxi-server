package ru.spring.localtaxi.authserviceimpl.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.spring.localtaxi.authserviceimpl.domain.Car;
import ru.spring.localtaxi.authserviceimpl.repository.CarRepository;
import ru.spring.localtaxi.authserviceimpl.service.CarService;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

  private final CarRepository repository;

  @Override
  public Car save(Car car) {
    return repository.save(car);
  }
}
