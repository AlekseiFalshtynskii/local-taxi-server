package ru.spring.localtaxi.queueserviceapi.dto;

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
public class PlaceInQueueDTO {

  @NotNull
  private Long id;

  @NotNull
  private int number;

  @NotNull
  private UserDTO driver;

  private Set<UserDTO> passengers;

  @NotNull
  private int numberPassengers;

  private LocalDateTime startDT;

  private LocalDateTime startFirstDT;

  private LocalDateTime endDT;

  public static PlaceInQueueDTO of(Long id, int number, UserDTO driver, Set<UserDTO> passengers,
      int numberPassengers, LocalDateTime startDT, LocalDateTime startFirstDT,
      LocalDateTime endDT) {
    return new PlaceInQueueDTO(id, number, driver, passengers, numberPassengers, startDT,
        startFirstDT, endDT);
  }
}
