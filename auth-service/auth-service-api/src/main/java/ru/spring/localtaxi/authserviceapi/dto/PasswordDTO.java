package ru.spring.localtaxi.authserviceapi.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordDTO {

  @NotBlank
  private String oldPassword;

  @NotBlank
  private String newPassword;
}
