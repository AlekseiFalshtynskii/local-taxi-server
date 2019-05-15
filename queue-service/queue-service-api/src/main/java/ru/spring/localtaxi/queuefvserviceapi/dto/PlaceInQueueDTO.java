package ru.spring.localtaxi.queuefvserviceapi.dto;

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

  public static PlaceInQueueDTO of(Long id, int number, UserDTO driver, Set<UserDTO> passengers,
      int numberPassengers) {
    return new PlaceInQueueDTO(id, number, driver, passengers, numberPassengers);
  }
}
