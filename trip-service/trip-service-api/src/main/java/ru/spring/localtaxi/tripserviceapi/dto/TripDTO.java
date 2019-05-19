package ru.spring.localtaxi.tripserviceapi.dto;

import java.time.LocalDateTime;
import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.spring.localtaxi.authserviceapi.dto.UserDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripDTO {

  @NotNull
  private Long id;

  @NotNull
  private String direction;

  @NotNull
  private UserDTO driver;

  private Set<UserDTO> passengers;

  @NotNull
  private int numberPassengers;

  @NotNull
  private LocalDateTime startDT;

  private LocalDateTime endDT;

  public static TripDTO of(Long id, String direction, UserDTO driver, Set<UserDTO> passengers,
      int numberPassengers,
      LocalDateTime startDT, LocalDateTime endDT) {
    return new TripDTO(id, direction, driver, passengers, numberPassengers, startDT, endDT);
  }
}
