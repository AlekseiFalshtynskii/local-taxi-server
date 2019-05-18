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
import ru.spring.localtaxi.queuefvserviceapi.client.PlaceInQueueFVClient;
import ru.spring.localtaxi.queuefvserviceapi.client.PlaceInQueueVFClient;
import ru.spring.localtaxi.queuefvserviceapi.dto.PlaceInQueueDTO;
import ru.spring.localtaxi.queuefvserviceapi.dto.TripDTO;
import ru.spring.localtaxi.queuefvserviceapi.enums.Directions;
import ru.spring.localtaxi.queuefvserviceimpl.domain.Trip;
import ru.spring.localtaxi.queuefvserviceimpl.repository.TripRepository;
import ru.spring.localtaxi.queuefvserviceimpl.service.TripService;

@Service
@AllArgsConstructor
public class TripServiceImpl implements TripService {

  private final TripRepository repository;

  private final UserClient userClient;

  private final PlaceInQueueFVClient placeInQueueFVClient;

  private final PlaceInQueueVFClient placeInQueueVFClient;

  @Override
  public TripDTO start() {
    Directions direction = Directions.FV;
    PlaceInQueueDTO placeInQueue = placeInQueueFVClient.findByDriver();
    if (placeInQueue == null) {
      direction = Directions.VF;
      placeInQueue = placeInQueueVFClient.findByDriver();
    }
    Trip trip = Trip.of(direction.getName(), placeInQueue.getDriver().getId(),
        placeInQueue.getPassengers().stream().map(UserDTO::getId).collect(Collectors.toSet()),
        placeInQueue.getNumberPassengers(), LocalDateTime.now(), null);
    if (Directions.FV.equals(direction)) {
      placeInQueueFVClient.removeDriverFromQueue();
    } else {
      placeInQueueVFClient.removeDriverFromQueue();
    }
    return fromEntity(repository.save(trip));
  }

  @Override
  public TripDTO finish() {
    UserDTO user = userClient.getCurrentUser();
    Trip trip = repository.findFirstByDriverIdAndEndDTIsNull(user.getId());
    trip.setEndDT(LocalDateTime.now());
    return fromEntity(repository.save(trip));
  }

  @Override
  public TripDTO findActive() {
    UserDTO user = userClient.getCurrentUser();
    if (user.isDriver()) {
      return fromEntity(repository.findFirstByDriverIdAndEndDTIsNull(user.getId()));
    }
    return fromEntity(repository.findFirstByPassengerIdsContainsAndEndDTIsNull(user.getId()));
  }

  @Override
  public List<TripDTO> findAllFinished() {
    UserDTO user = userClient.getCurrentUser();
    if (user.isDriver()) {
      return repository.findByDriverIdAndEndDTIsNotNullOrderByStartDTDesc(user.getId())
          .stream().map(this::fromEntity).collect(Collectors.toList());
    }
    return repository.findByPassengerIdsContainsAndEndDTIsNotNullOrderByStartDTDesc(user.getId())
        .stream().map(this::fromEntity).collect(Collectors.toList());
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
          .of(trip.getId(), trip.getDirection(), driver, passengers, trip.getNumberPassengers(),
              trip.getStartDT(), trip.getEndDT());
    }
    return null;
  }
}
