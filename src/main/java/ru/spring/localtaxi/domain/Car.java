package ru.spring.localtaxi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "cars")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "model", nullable = false)
    private String model;

    @NotBlank
    @Column(name = "reg_number", nullable = false)
    private String regNumber;

    @NotBlank
    @Column(name = "color", nullable = false)
    private String color;

    private Car(String model, String regNumber, String color) {
        this.model = model;
        this.regNumber = regNumber;
        this.color = color;
    }

    public static Car of(String model, String regNumber, String color) {
        return new Car(model, regNumber, color);
    }
}
