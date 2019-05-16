package ru.spring.localtaxi.queuefvserviceimpl.service;

import java.util.List;
import ru.spring.localtaxi.queuefvserviceapi.dto.TripDTO;

public interface TripService {

  TripDTO findByDriverActive();

  TripDTO findByPassengerActive();

  List<TripDTO> findByDriver();

  List<TripDTO> findByPassenger();

  TripDTO start();

  TripDTO finish();
}
