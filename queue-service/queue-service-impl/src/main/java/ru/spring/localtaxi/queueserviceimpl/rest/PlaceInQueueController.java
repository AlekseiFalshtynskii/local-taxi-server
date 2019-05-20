package ru.spring.localtaxi.queueserviceimpl.rest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.spring.localtaxi.queueserviceapi.api.PlaceInQueueApi;
import ru.spring.localtaxi.queueserviceapi.dto.PlaceInQueueDTO;
import ru.spring.localtaxi.queueserviceapi.dto.StatisticDTO;
import ru.spring.localtaxi.queueserviceimpl.service.PlaceInQueueService;

@RestController
@AllArgsConstructor
public class PlaceInQueueController implements PlaceInQueueApi {

  private final PlaceInQueueService service;

  @HystrixCommand
  @Override
  public List<PlaceInQueueDTO> getAllActive() {
    return service.findAll();
  }

  @HystrixCommand
  @Override
  public PlaceInQueueDTO getCurrent() {
    return service.findCurrent();
  }

  @HystrixCommand
  @Override
  public List<PlaceInQueueDTO> addDriverInQueue() {
    return service.addDriverInQueue();
  }

  @HystrixCommand
  @Override
  public List<PlaceInQueueDTO> removeDriverFromQueue() {
    return service.removeDriverFromQueue();
  }

  @HystrixCommand
  @Override
  public List<PlaceInQueueDTO> addPassengerInQueue(Long piqId) {
    return service.addPassengerInQueue(piqId);
  }

  @HystrixCommand
  @Override
  public List<PlaceInQueueDTO> removePassengerFromQueue(Long passengerId) {
    return service.removePassengerFromQueue(passengerId);
  }

  @HystrixCommand
  @Override
  public List<PlaceInQueueDTO> getAllWithEndDT() {
    return service.findAllEndWithEndDT();
  }

  @HystrixCommand
  @Override
  public StatisticDTO getStatistic() {
    return service.getStatistic();
  }
}
