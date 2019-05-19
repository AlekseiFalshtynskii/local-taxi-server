package ru.spring.localtaxi.queueserviceimpl.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.localtaxi.authserviceapi.client.UserClient;
import ru.spring.localtaxi.authserviceapi.client.UserUnauthClient;
import ru.spring.localtaxi.authserviceapi.dto.UserDTO;
import ru.spring.localtaxi.queueserviceapi.client.PlaceInQueueVFClient;
import ru.spring.localtaxi.queueserviceapi.dto.PlaceInQueueDTO;
import ru.spring.localtaxi.queueserviceapi.dto.StatisticDTO;
import ru.spring.localtaxi.queueserviceimpl.domain.PlaceInQueue;
import ru.spring.localtaxi.queueserviceimpl.repository.PlaceInQueueRepository;
import ru.spring.localtaxi.queueserviceimpl.service.PlaceInQueueService;

@Service
@AllArgsConstructor
public class PlaceInQueueServiceImpl implements PlaceInQueueService {

  private final PlaceInQueueRepository repository;

  private final UserUnauthClient userUnauthClient;

  private final UserClient userClient;

  private final PlaceInQueueVFClient placeInQueueVFClient;

  @Override
  public List<PlaceInQueueDTO> findAll() {
    return repository.findAllByEndDTIsNullOrderByNumberAsc().stream().map(this::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public PlaceInQueueDTO findCurrent() {
    UserDTO user = userClient.getCurrentUser();
    if (user.isDriver()) {
      return fromEntity(repository.findByDriverIdAndEndDTIsNull(user.getId()));
    }
    return fromEntity(repository.findByPassengerIdsContainsAndEndDTIsNull(user.getId()));
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

  @Override
  public List<PlaceInQueueDTO> findAllEndWithEndDT() {
    return repository.findAllByEndDTIsNotNull().stream().map(this::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public StatisticDTO getStatistic() {
    long durationFV = 0;
    long durationVF = 0;
    long waitingDriverInQueue = 0;
    long waitingPassengers = 0;

    List<PlaceInQueue> piqsFV = repository.findAllByEndDTIsNotNull();
    if (!piqsFV.isEmpty()) {
      durationFV = piqsFV.parallelStream().mapToLong(
          placeInQueue -> ChronoUnit.MILLIS
              .between(placeInQueue.getStartDT(), placeInQueue.getEndDT())).sum();
    }

    List<PlaceInQueueDTO> piqsVF = placeInQueueVFClient.getAllWithEndDT();
    if (!piqsVF.isEmpty()) {
      durationVF = piqsVF.parallelStream().mapToLong(
          placeInQueue -> ChronoUnit.MILLIS
              .between(placeInQueue.getStartDT(), placeInQueue.getEndDT())).sum();
    }

    if (!piqsFV.isEmpty() || !piqsVF.isEmpty()) {
      waitingDriverInQueue = (durationFV + durationVF) / (piqsFV.size() + piqsVF.size());
    }

    if (!piqsFV.isEmpty()) {
      durationFV = piqsFV.parallelStream().mapToLong(
          placeInQueue -> ChronoUnit.MILLIS
              .between(placeInQueue.getStartFirstDT(), placeInQueue.getEndDT())).sum();
    }

    if (!piqsVF.isEmpty()) {
      durationVF = piqsVF.parallelStream().mapToLong(
          placeInQueue -> ChronoUnit.MILLIS
              .between(placeInQueue.getStartFirstDT(), placeInQueue.getEndDT())).sum();
    }

    if (!piqsFV.isEmpty() || !piqsVF.isEmpty()) {
      waitingPassengers = (durationFV + durationVF) / (piqsFV.size() + piqsVF.size());
    }
    return StatisticDTO.of(waitingDriverInQueue, waitingPassengers);
  }

  private PlaceInQueueDTO fromEntity(PlaceInQueue placeInQueue) {
    if (placeInQueue != null) {
      UserDTO driver = userUnauthClient.getUserById(placeInQueue.getDriverId());
      Set<UserDTO> passengers = new HashSet<>();
      if (!Objects.isNull(placeInQueue.getPassengerIds())) {
        placeInQueue.getPassengerIds().stream().map(userUnauthClient::getUserById)
            .forEach(passengers::add);
      }
      return PlaceInQueueDTO.of(placeInQueue.getId(), placeInQueue.getNumber(), driver, passengers,
          placeInQueue.getNumberPassengers(), placeInQueue.getStartDT(),
          placeInQueue.getStartFirstDT(), placeInQueue.getEndDT());
    }
    return null;
  }
}
