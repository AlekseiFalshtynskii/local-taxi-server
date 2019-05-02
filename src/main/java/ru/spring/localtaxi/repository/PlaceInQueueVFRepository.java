package ru.spring.localtaxi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.localtaxi.domain.PlaceInQueueVF;
import ru.spring.localtaxi.domain.User;

import java.util.List;
import java.util.Optional;

public interface PlaceInQueueVFRepository extends JpaRepository<PlaceInQueueVF, Long> {

    PlaceInQueueVF findFirstByOrderByNumberDesc();

    Optional<PlaceInQueueVF> findByDriverId(Long driverId);

    Optional<PlaceInQueueVF> findByPassengersContains(User passenger);

    List<PlaceInQueueVF> findAllByNumberIsAfter(int number);
}
