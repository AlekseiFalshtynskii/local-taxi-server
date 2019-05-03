package ru.spring.localtaxi.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.spring.localtaxi.domain.Car;
import ru.spring.localtaxi.service.CarService;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class CarController {

    private final CarService service;

    @PostMapping("/api/cars")
    public Car saveCar(@Valid @RequestBody Car car) {
        return service.save(car);
    }
}
