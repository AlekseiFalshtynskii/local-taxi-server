package ru.spring.localtaxi.authserviceapi.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FioDTO {

  @NotBlank
  private String firstName;

  private String lastName;

  private String middleName;
}
