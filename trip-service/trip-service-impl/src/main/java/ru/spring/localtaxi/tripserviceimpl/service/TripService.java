package ru.spring.localtaxi.tripserviceimpl.service;

import java.util.List;
import ru.spring.localtaxi.tripserviceapi.dto.StatisticDTO;
import ru.spring.localtaxi.tripserviceapi.dto.TripDTO;

public interface TripService {

  TripDTO start();

  TripDTO finish();

  TripDTO findActive();

  List<TripDTO> findAllFinished();

  StatisticDTO getStatistic();
}
