package ru.spring.localtaxi.authserviceimpl.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cars")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car implements Serializable {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
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

  @Version
  private Long version;

  public static Car of(String model, String regNumber, String color) {
    return new Car(null, model, regNumber, color, null);
  }
}
