package ru.spring.localtaxi.queuefvserviceimpl.service.impl;

import java.time.LocalDateTime;
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
    return repository.findAllByEndDTIsNullOrderByNumberAsc().stream().map(this::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public PlaceInQueueDTO findByDriver() {
    return fromEntity(repository.findByDriverIdAndEndDTIsNull(userClient.getCurrentUser().getId()));
  }

  @Override
  public PlaceInQueueDTO findByPassenger() {
    return fromEntity(
        repository.findByPassengerIdsContainsAndEndDTIsNull(userClient.getCurrentUser().getId()));
  }

  @Transactional
  @Override
  public List<PlaceInQueueDTO> addDriverInQueue() {
    UserDTO driver = userClient.getCurrentUser();
    PlaceInQueue lastPlaceInQueue = repository.findFirstByEndDTIsNullOrderByNumberDesc();
    int number = lastPlaceInQueue == null ? 1 : lastPlaceInQueue.getNumber() + 1;
    repository.save(PlaceInQueue.of(number, driver.getId(), 0, LocalDateTime.now(),
        number == 1 ? LocalDateTime.now() : null, null));
    return findAll();
  }

  @Transactional
  @Override
  public List<PlaceInQueueDTO> removeDriverFromQueue() {
    UserDTO driver = userClient.getCurrentUser();
    PlaceInQueue placeInQueue = repository.findByDriverIdAndEndDTIsNull(driver.getId());
    if (placeInQueue != null) {
      List<PlaceInQueue> placeInQueueList = repository
          .findAllByNumberIsAfterAndEndDTIsNull(placeInQueue.getNumber());
      placeInQueueList.forEach(p -> {
        p.setNumber(p.getNumber() - 1);
        if (p.getNumber() == 1) {
          p.setStartFirstDT(LocalDateTime.now());
        }
      });
      placeInQueue.setEndDT(LocalDateTime.now());
      repository.save(placeInQueue);
      repository.saveAll(placeInQueueList);
    }
    return findAll();
  }

  @Transactional
  @Override
  public List<PlaceInQueueDTO> addPassengerInQueue(Long piqId) {
    UserDTO user = userClient.getCurrentUser();
    PlaceInQueue placeInQueue;
    if (user.isDriver()) {
      placeInQueue = repository.findByDriverIdAndEndDTIsNull(user.getId());
    } else {
      placeInQueue = repository.findById(piqId).orElse(null);
    }
    if (placeInQueue != null && placeInQueue.getNumberPassengers() < 4) {
      if (user.isPassenger()) {
        if (!placeInQueue.getPassengerIds().contains(user.getId())) {
          placeInQueue.getPassengerIds().add(user.getId());
        }
      }
      placeInQueue.setNumberPassengers(placeInQueue.getNumberPassengers() + 1);
      repository.save(placeInQueue);
    }
    return findAll();
  }

  @Transactional
  @Override
  public List<PlaceInQueueDTO> removePassengerFromQueue(Long passengerId) {
    UserDTO user = userClient.getCurrentUser();
    PlaceInQueue placeInQueue;
    if (user.isDriver()) {
      placeInQueue = repository.findByDriverIdAndEndDTIsNull(user.getId());
    } else {
      placeInQueue = repository.findByPassengerIdsContainsAndEndDTIsNull(user.getId());
    }
    if (placeInQueue != null && placeInQueue.getNumberPassengers() > 0) {
      if (passengerId != null) {
        placeInQueue.getPassengerIds().remove(passengerId);
      } else if (user.isPassenger()) {
        placeInQueue.getPassengerIds().remove(user.getId());
      }
      placeInQueue.setNumberPassengers(placeInQueue.getNumberPassengers() - 1);
      repository.save(placeInQueue);
    }
    return findAll();
  }

  private PlaceInQueueDTO fromEntity(PlaceInQueue placeInQueue) {
    if (placeInQueue != null) {
      UserDTO driver = userClient.getUserById(placeInQueue.getDriverId());
      Set<UserDTO> passengers = new HashSet<>();
      if (!Objects.isNull(placeInQueue.getPassengerIds())) {
        placeInQueue.getPassengerIds().stream().map(userClient::getUserById)
            .forEach(passengers::add);
      }
      return PlaceInQueueDTO.of(placeInQueue.getId(), placeInQueue.getNumber(), driver, passengers,
          placeInQueue.getNumberPassengers(), placeInQueue.getStartDT(),
          placeInQueue.getStartFirstDT(), placeInQueue.getEndDT());
    }
    return null;
  }
}
