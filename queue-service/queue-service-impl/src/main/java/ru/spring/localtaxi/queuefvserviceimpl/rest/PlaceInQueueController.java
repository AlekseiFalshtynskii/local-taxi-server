package ru.spring.localtaxi.queuefvserviceimpl.rest;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.spring.localtaxi.queuefvserviceapi.api.PlaceInQueueApi;
import ru.spring.localtaxi.queuefvserviceapi.dto.PlaceInQueueDTO;
import ru.spring.localtaxi.queuefvserviceimpl.service.PlaceInQueueService;

@RestController
@AllArgsConstructor
public class PlaceInQueueController implements PlaceInQueueApi {

  private final PlaceInQueueService service;

  @Override
  public List<PlaceInQueueDTO> getAll() {
    return service.findAll();
  }

  @Override
  public PlaceInQueueDTO getCurrent() {
    return service.findCurrent();
  }

  @Override
  public List<PlaceInQueueDTO> addDriverInQueue() {
    return service.addDriverInQueue();
  }

  @Override
  public List<PlaceInQueueDTO> removeDriverFromQueue() {
    return service.removeDriverFromQueue();
  }

  @Override
  public List<PlaceInQueueDTO> addPassengerInQueue(Long piqId) {
    return service.addPassengerInQueue(piqId);
  }

  @Override
  public List<PlaceInQueueDTO> removePassengerFromQueue(Long passengerId) {
    return service.removePassengerFromQueue(passengerId);
  }
}
