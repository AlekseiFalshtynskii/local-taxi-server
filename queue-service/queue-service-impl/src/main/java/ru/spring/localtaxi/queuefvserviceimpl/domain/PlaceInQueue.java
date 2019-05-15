package ru.spring.localtaxi.queuefvserviceimpl.domain;

import static javax.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.LazyCollectionOption.FALSE;

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
@Table(name = "queue")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceInQueue {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(name = "number", nullable = false)
  private int number;

  @Column(name = "driver_id", nullable = false)
  private Long driverId;

  @ElementCollection
  @LazyCollection(FALSE)
  @Column(name = "passenger_id")
  private Set<Long> passengerIds;

  @Column(name = "number_passengers")
  private int numberPassengers;

  @Version
  private Long version;

  public static PlaceInQueue of(int number, Long driverId, int numberPassengers) {
    return new PlaceInQueue(null, number, driverId, null, numberPassengers, null);
  }
}
