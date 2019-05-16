package ru.spring.localtaxi.queuefvserviceimpl.service;

import java.util.List;
import ru.spring.localtaxi.queuefvserviceapi.dto.PlaceInQueueDTO;

public interface PlaceInQueueService {

  List<PlaceInQueueDTO> findAll();

  PlaceInQueueDTO findByDriver();

  PlaceInQueueDTO findByPassenger();

  List<PlaceInQueueDTO> addDriverInQueue();

  List<PlaceInQueueDTO> removeDriverFromQueue();

  List<PlaceInQueueDTO> addPassengerInQueue(Long piqId);

  List<PlaceInQueueDTO> removePassengerFromQueue(Long passengerId);
}
