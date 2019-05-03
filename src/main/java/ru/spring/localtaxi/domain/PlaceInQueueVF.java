package ru.spring.localtaxi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.LazyCollectionOption.FALSE;

@Entity
@Table(name = "queue_v_f")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceInQueueVF {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false)
    private int number;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @LazyCollection(value = FALSE)
    @JoinTable(
            name = "drivers_in_queue_v_f",
            joinColumns = @JoinColumn(
                    name = "queue_v_f_id",
                    nullable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "driver_id",
                    nullable = false,
                    unique = true
            )
    )
    private User driver;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @LazyCollection(value = FALSE)
    @JoinTable(
            name = "passengers_in_queue_v_f",
            joinColumns = @JoinColumn(
                    name = "queue_v_f_id",
                    nullable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "passenger_id",
                    nullable = false,
                    unique = true
            )
    )
    private Set<User> passengers;

    @Column(name = "number_passengers")
    private int numberPassengers;

    @Version
    private Long version;

    private PlaceInQueueVF(int number, User driver, int numberPassengers) {
        this.number = number;
        this.driver = driver;
        this.numberPassengers = numberPassengers;
    }

    public static PlaceInQueueVF of(int number, User driver, int numberPassengers) {
        return new PlaceInQueueVF(number, driver, numberPassengers);
    }
}
