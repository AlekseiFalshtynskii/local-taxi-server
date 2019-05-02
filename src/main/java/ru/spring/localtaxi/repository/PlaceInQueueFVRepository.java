package ru.spring.localtaxi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.localtaxi.domain.PlaceInQueueFV;
import ru.spring.localtaxi.domain.User;

import java.util.List;
import java.util.Optional;

public interface PlaceInQueueFVRepository extends JpaRepository<PlaceInQueueFV, Long> {

    PlaceInQueueFV findFirstByOrderByNumberDesc();

    Optional<PlaceInQueueFV> findByDriverId(Long driverId);

    Optional<PlaceInQueueFV> findByPassengersContains(User passenger);

    List<PlaceInQueueFV> findAllByNumberIsAfter(int number);
}
