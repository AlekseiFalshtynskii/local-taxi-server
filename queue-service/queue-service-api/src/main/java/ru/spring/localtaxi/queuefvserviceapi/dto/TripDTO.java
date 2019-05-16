package ru.spring.localtaxi.queuefvserviceapi.dto;

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
  private UserDTO driver;

  private Set<UserDTO> passengers;

  @NotNull
  private int numberPassengers;

  @NotNull
  private LocalDateTime startDT;

  private LocalDateTime endDT;

  public static TripDTO of(Long id, UserDTO driver, Set<UserDTO> passengers, int numberPassengers,
      LocalDateTime startDT, LocalDateTime endDT) {
    return new TripDTO(id, driver, passengers, numberPassengers, startDT, endDT);
  }
}
