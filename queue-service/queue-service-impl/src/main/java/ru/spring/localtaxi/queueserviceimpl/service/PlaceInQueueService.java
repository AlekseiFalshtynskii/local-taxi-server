package ru.spring.localtaxi.queueserviceimpl.service;

import java.util.List;
import ru.spring.localtaxi.queueserviceapi.dto.PlaceInQueueDTO;
import ru.spring.localtaxi.queueserviceapi.dto.StatisticDTO;

public interface PlaceInQueueService {

  List<PlaceInQueueDTO> findAll();

  PlaceInQueueDTO findCurrent();

  List<PlaceInQueueDTO> addDriverInQueue();

  List<PlaceInQueueDTO> removeDriverFromQueue();

  List<PlaceInQueueDTO> addPassengerInQueue(Long piqId);

  List<PlaceInQueueDTO> removePassengerFromQueue(Long passengerId);

  List<PlaceInQueueDTO> findAllEndWithEndDT();

  StatisticDTO getStatistic();
}
