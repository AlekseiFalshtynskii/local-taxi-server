package ru.spring.localtaxi.queuefvserviceapi.api;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spring.localtaxi.queuefvserviceapi.dto.PlaceInQueueDTO;

public interface PlaceInQueueApi {

  @GetMapping("/api/queue")
  List<PlaceInQueueDTO> findAll();

  @GetMapping("/api/queue/driver")
  PlaceInQueueDTO findByDriver();

  @GetMapping("/api/queue/passenger")
  PlaceInQueueDTO findByPassenger();

  @PostMapping("/api/queue/driver")
  List<PlaceInQueueDTO> addDriverInQueue();

  @DeleteMapping("/api/queue/driver")
  List<PlaceInQueueDTO> removeDriverFromQueue();

  @PostMapping({"/api/queue/passenger"})
  List<PlaceInQueueDTO> addPassengerInQueue(
      @RequestParam(name = "piqId") Long piqId,
      @RequestParam(name = "passengerId", required = false) Long passengerId
  );

  @DeleteMapping({"/api/queue/passenger"})
  List<PlaceInQueueDTO> removePassengerFromQueue(
      @RequestParam(name = "piqId") Long piqId,
      @RequestParam(name = "passengerId", required = false) Long passengerId
  );
}
