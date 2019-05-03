package ru.spring.localtaxi.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.spring.localtaxi.domain.Car;
import ru.spring.localtaxi.repository.CarRepository;
import ru.spring.localtaxi.service.CarService;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository repository;

    @Override
    public Car save(Car car) {
        return repository.save(car);
    }
}
