package ru.spring.localtaxi.queuefvserviceimpl.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.spring.localtaxi.authserviceapi.client.UserClient;
import ru.spring.localtaxi.authserviceapi.dto.UserDTO;
import ru.spring.localtaxi.queuefvserviceapi.dto.PlaceInQueueDTO;
import ru.spring.localtaxi.queuefvserviceapi.dto.TripDTO;
import ru.spring.localtaxi.queuefvserviceimpl.domain.Trip;
import ru.spring.localtaxi.queuefvserviceimpl.repository.TripRepository;
import ru.spring.localtaxi.queuefvserviceimpl.service.PlaceInQueueService;
import ru.spring.localtaxi.queuefvserviceimpl.service.TripService;

@Service
@AllArgsConstructor
public class TripServiceImpl implements TripService {

  private final TripRepository repository;

  private final PlaceInQueueService placeInQueueService;

  private final UserClient userClient;

  @Override
  public TripDTO findByDriverActive() {
    return fromEntity(
        repository.findFirstByDriverIdAndEndDTIsNull(userClient.getCurrentUser().getId()));
  }

  @Override
  public TripDTO findByPassengerActive() {
    return fromEntity(
        repository
            .findFirstByPassengerIdsContainsAndEndDTIsNull(userClient.getCurrentUser().getId()));
  }

  @Override
  public List<TripDTO> findByDriver() {
    return repository
        .findByDriverIdAndEndDTIsNotNullOrderByStartDTDesc(userClient.getCurrentUser().getId())
        .stream().map(this::fromEntity).collect(Collectors.toList());
  }

  @Override
  public List<TripDTO> findByPassenger() {
    return repository.findByPassengerIdsContainsAndEndDTIsNotNullOrderByStartDTDesc(
        userClient.getCurrentUser().getId()).stream().map(this::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public TripDTO start() {
    PlaceInQueueDTO placeInQueue = placeInQueueService.findByDriver();
    Trip trip = Trip.of(placeInQueue.getDriver().getId(),
        placeInQueue.getPassengers().stream().map(UserDTO::getId).collect(Collectors.toSet()),
        placeInQueue.getNumberPassengers(), LocalDateTime.now(), null);
    placeInQueueService.removeDriverFromQueue();
    return fromEntity(repository.save(trip));
  }

  @Override
  public TripDTO finish() {
    UserDTO user = userClient.getCurrentUser();
    Trip trip = repository.findFirstByDriverIdAndEndDTIsNull(user.getId());
    trip.setEndDT(LocalDateTime.now());
    return fromEntity(repository.save(trip));
  }

  private TripDTO fromEntity(Trip trip) {
    if (trip != null) {
      UserDTO driver = userClient.getUserById(trip.getDriverId());
      Set<UserDTO> passengers = new HashSet<>();
      if (!Objects.isNull(trip.getPassengerIds())) {
        trip.getPassengerIds().stream().map(userClient::getUserById)
            .forEach(passengers::add);
      }
      return TripDTO
          .of(trip.getId(), driver, passengers, trip.getNumberPassengers(), trip.getStartDT(),
              trip.getEndDT());
    }
    return null;
  }
}
