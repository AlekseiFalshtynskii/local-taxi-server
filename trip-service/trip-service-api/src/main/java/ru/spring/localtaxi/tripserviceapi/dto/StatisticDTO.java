package ru.spring.localtaxi.tripserviceapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDTO {

  private long durationTrip;

  public static StatisticDTO of(long durationTrip) {
    return new StatisticDTO(durationTrip);
  }
}
