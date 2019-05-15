package ru.spring.localtaxi.queuefvserviceimpl.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.localtaxi.authserviceapi.client.UserClient;
import ru.spring.localtaxi.authserviceapi.dto.UserDTO;
import ru.spring.localtaxi.queuefvserviceapi.dto.PlaceInQueueDTO;
import ru.spring.localtaxi.queuefvserviceimpl.domain.PlaceInQueue;
import ru.spring.localtaxi.queuefvserviceimpl.repository.PlaceInQueueRepository;
import ru.spring.localtaxi.queuefvserviceimpl.service.PlaceInQueueService;

@Service
@AllArgsConstructor
public class PlaceInQueueServiceImpl implements PlaceInQueueService {

  private final PlaceInQueueRepository repository;

  private final UserClient userClient;

  @Override
  public List<PlaceInQueueDTO> findAll() {
    return repository.findAll().stream().map(this::fromPlaceInQueue).collect(Collectors.toList());
  }

  @Override
  public PlaceInQueueDTO findByDriver() {
    return fromPlaceInQueue(repository.findByDriverId(userClient.getCurrentUser().getId()));
  }

  @Override
  public PlaceInQueueDTO findByPassenger() {
    return fromPlaceInQueue(
        repository.findByPassengerIdsContains(userClient.getCurrentUser().getId()));
  }

  @Transactional
  @Override
  public List<PlaceInQueueDTO> addDriverInQueue() {
    UserDTO driver = userClient.getCurrentUser();
    PlaceInQueue lastPlaceInQueue = repository.findFirstByOrderByNumberDesc();
    int number = lastPlaceInQueue == null ? 1 : lastPlaceInQueue.getNumber() + 1;
    repository.save(PlaceInQueue.of(number, driver.getId(), 0));
    return findAll();
  }

  @Transactional
  @Override
  public List<PlaceInQueueDTO> removeDriverFromQueue() {
    UserDTO driver = userClient.getCurrentUser();
    PlaceInQueue placeInQueue = repository.findByDriverId(driver.getId());
    if (placeInQueue != null) {
      List<PlaceInQueue> placeInQueueList = repository
          .findAllByNumberIsAfter(placeInQueue.getNumber());
      placeInQueueList.forEach(p -> p.setNumber(p.getNumber() - 1));
      repository.saveAll(placeInQueueList);
      repository.delete(placeInQueue);
    }
    return findAll();
  }

  @Transactional
  @Override
  public List<PlaceInQueueDTO> addPassengerInQueue(Long piqId, Long passengerId) {
    PlaceInQueue placeInQueue = repository.findById(piqId).orElse(null);
    if (placeInQueue != null && placeInQueue.getNumberPassengers() < 4) {
      if (passengerId != null) {
        UserDTO passenger = userClient.getUserById(passengerId);
        if (!placeInQueue.getPassengerIds().contains(passenger.getId())) {
          placeInQueue.getPassengerIds().add(passenger.getId());
        }
      }
      placeInQueue.setNumberPassengers(placeInQueue.getNumberPassengers() + 1);
      repository.save(placeInQueue);
    }
    return findAll();
  }

  @Transactional
  @Override
  public List<PlaceInQueueDTO> removePassengerFromQueue(Long piqId, Long passengerId) {
    PlaceInQueue placeInQueue = repository.findById(piqId).orElse(null);
    if (placeInQueue != null && placeInQueue.getNumberPassengers() > 0) {
      if (passengerId != null) {
        UserDTO passenger = userClient.getUserById(passengerId);
        placeInQueue.getPassengerIds().remove(passenger.getId());
      }
      placeInQueue.setNumberPassengers(placeInQueue.getNumberPassengers() - 1);
      repository.save(placeInQueue);
    }
    return findAll();
  }

  private PlaceInQueueDTO fromPlaceInQueue(PlaceInQueue placeInQueue) {
    if (placeInQueue != null) {
      UserDTO driver = userClient.getUserById(placeInQueue.getDriverId());
      Set<UserDTO> passengers = new HashSet<>();
      if (!Objects.isNull(placeInQueue.getPassengerIds())) {
        placeInQueue.getPassengerIds().stream().map(userClient::getUserById)
            .forEach(passengers::add);
      }
      return PlaceInQueueDTO.of(placeInQueue.getId(), placeInQueue.getNumber(), driver, passengers,
          placeInQueue.getNumberPassengers());
    }
    return null;
  }
}
