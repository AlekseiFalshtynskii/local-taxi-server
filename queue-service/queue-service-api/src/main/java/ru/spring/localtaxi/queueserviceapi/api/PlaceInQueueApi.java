package ru.spring.localtaxi.queueserviceapi.api;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spring.localtaxi.queueserviceapi.dto.PlaceInQueueDTO;
import ru.spring.localtaxi.queueserviceapi.dto.StatisticDTO;

public interface PlaceInQueueApi {

  @GetMapping("/api/queue")
  List<PlaceInQueueDTO> getAllActive();

  @GetMapping("/api/queue/current")
  PlaceInQueueDTO getCurrent();

  @PostMapping("/api/queue/driver")
  List<PlaceInQueueDTO> addDriverInQueue();

  @DeleteMapping("/api/queue/driver")
  List<PlaceInQueueDTO> removeDriverFromQueue();

  @PostMapping("/api/queue/passenger")
  List<PlaceInQueueDTO> addPassengerInQueue(
      @RequestParam(name = "piqId", required = false) Long piqId
  );

  @DeleteMapping("/api/queue/passenger")
  List<PlaceInQueueDTO> removePassengerFromQueue(
      @RequestParam(name = "passengerId", required = false) Long passengerId
  );

  @GetMapping("/api/queue/end")
  List<PlaceInQueueDTO> getAllWithEndDT();

  @GetMapping({"/api/queue/statistic"})
  StatisticDTO getStatistic();
}
