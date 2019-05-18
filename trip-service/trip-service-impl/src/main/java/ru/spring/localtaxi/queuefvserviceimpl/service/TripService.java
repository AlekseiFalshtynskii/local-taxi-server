package ru.spring.localtaxi.queuefvserviceimpl.service;

import java.util.List;
import ru.spring.localtaxi.queuefvserviceapi.dto.TripDTO;

public interface TripService {

  TripDTO start();

  TripDTO finish();

  TripDTO findActive();

  List<TripDTO> findAllFinished();
}
