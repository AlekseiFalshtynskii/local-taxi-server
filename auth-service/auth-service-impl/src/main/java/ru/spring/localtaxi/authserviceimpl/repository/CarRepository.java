package ru.spring.localtaxi.authserviceimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.localtaxi.authserviceimpl.domain.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
