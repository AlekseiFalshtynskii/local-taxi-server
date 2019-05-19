package ru.spring.localtaxi.queueserviceapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDTO {

  private long waitingDriverInQueue;

  private long waitingPassengers;

  public static StatisticDTO of(long waitingDriverInQueue, long waitingPassengers) {
    return new StatisticDTO(waitingDriverInQueue, waitingPassengers);
  }
}
