package ru.spring.localtaxi.queuefvserviceimpl.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.localtaxi.queuefvserviceimpl.domain.PlaceInQueue;

public interface PlaceInQueueRepository extends JpaRepository<PlaceInQueue, Long> {

  PlaceInQueue findFirstByOrderByNumberDesc();

  PlaceInQueue findByDriverId(Long id);

  PlaceInQueue findByPassengerIdsContains(Long id);

  List<PlaceInQueue> findAllByNumberIsAfter(int number);
}
