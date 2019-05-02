package ru.spring.localtaxi.service;

import ru.spring.localtaxi.domain.PlaceInQueueFV;

import java.util.List;

public interface PlaceInQueueFVService {

    List<PlaceInQueueFV> findAll();

    PlaceInQueueFV findByDriverId(Long driverId);

    PlaceInQueueFV findByPassengerId(Long passengerId);

    List<PlaceInQueueFV> addDriverInQueue(Long driverId);

    List<PlaceInQueueFV> removeDriverFromQueue(Long placeInQueueFVId);

    List<PlaceInQueueFV> addPassengerInQueue(Long placeInQueueId, Long passengerId);

    List<PlaceInQueueFV> removePassengerFromQueue(Long placeInQueueId, Long passengerId);
}
