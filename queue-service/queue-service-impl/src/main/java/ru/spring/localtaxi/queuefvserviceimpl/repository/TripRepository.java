package ru.spring.localtaxi.queuefvserviceimpl.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.localtaxi.queuefvserviceimpl.domain.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {

  Trip findFirstByDriverIdAndEndDTIsNull(Long driverId);

  Trip findFirstByPassengerIdsContainsAndEndDTIsNull(Long passengerId);

  List<Trip> findByDriverIdAndEndDTIsNotNullOrderByStartDTDesc(Long driverId);

  List<Trip> findByPassengerIdsContainsAndEndDTIsNotNullOrderByStartDTDesc(Long passengerId);
}
