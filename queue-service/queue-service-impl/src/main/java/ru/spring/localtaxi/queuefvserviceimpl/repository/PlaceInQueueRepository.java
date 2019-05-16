package ru.spring.localtaxi.queuefvserviceimpl.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.localtaxi.queuefvserviceimpl.domain.PlaceInQueue;

public interface PlaceInQueueRepository extends JpaRepository<PlaceInQueue, Long> {

  List<PlaceInQueue> findAllByEndDTIsNullOrderByNumberAsc();

  PlaceInQueue findFirstByEndDTIsNullOrderByNumberDesc();

  PlaceInQueue findByDriverIdAndEndDTIsNull(Long id);

  PlaceInQueue findByPassengerIdsContainsAndEndDTIsNull(Long id);

  List<PlaceInQueue> findAllByNumberIsAfterAndEndDTIsNull(int number);
}
