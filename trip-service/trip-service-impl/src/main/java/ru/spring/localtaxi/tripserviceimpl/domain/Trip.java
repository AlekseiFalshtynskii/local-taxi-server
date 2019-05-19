package ru.spring.localtaxi.tripserviceimpl.domain;

import static javax.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.LazyCollectionOption.FALSE;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;

@Entity
@Table(name = "trips")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trip {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(name = "direction", nullable = false)
  private String direction;

  @Column(name = "driver_id", nullable = false)
  private Long driverId;

  @ElementCollection
  @LazyCollection(FALSE)
  @Column(name = "passenger_id")
  private Set<Long> passengerIds;

  @Column(name = "number_passengers")
  private int numberPassengers;

  @Column(name = "start_dt", nullable = false)
  private LocalDateTime startDT;

  @Column(name = "end_dt")
  private LocalDateTime endDT;

  @Version
  private Long version;

  public static Trip of(String direction, Long driverId, Set<Long> passengerIds,
      int numberPassengers,
      LocalDateTime startDT, LocalDateTime endDT) {
    return new Trip(null, direction, driverId, passengerIds, numberPassengers, startDT, endDT,
        null);
  }
}
