package ru.spring.localtaxi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.localtaxi.domain.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}
