package ru.spring.localtaxi.service;

import ru.spring.localtaxi.domain.PlaceInQueueVF;

import java.util.List;

public interface PlaceInQueueVFService {

    List<PlaceInQueueVF> findAll();

    PlaceInQueueVF findByDriverId(Long driverId);

    PlaceInQueueVF findByPassengerId(Long passengerId);

    List<PlaceInQueueVF> addDriverInQueue(Long driverId);

    List<PlaceInQueueVF> removeDriverFromQueue(Long placeInQueueFVId);

    List<PlaceInQueueVF> addPassengerInQueue(Long placeInQueueId, Long passengerId);

    List<PlaceInQueueVF> removePassengerFromQueue(Long placeInQueueId, Long passengerId);
}
