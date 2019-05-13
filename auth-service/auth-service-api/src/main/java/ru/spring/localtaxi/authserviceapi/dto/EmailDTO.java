package ru.spring.localtaxi.authserviceapi.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailDTO {

  @NotBlank
  private String email;
}
