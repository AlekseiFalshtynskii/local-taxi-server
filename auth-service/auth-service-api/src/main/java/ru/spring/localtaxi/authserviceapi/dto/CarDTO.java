package ru.spring.localtaxi.authserviceapi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {

  @NotNull
  private Long id;

  @NotBlank
  private String model;

  @NotBlank
  private String regNumber;

  @NotBlank
  private String color;

  public static CarDTO of(Long id, String model, String regNumber, String color) {
    return new CarDTO(id, model, regNumber, color);
  }
}
