package ru.spring.localtaxi.authserviceapi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityDTO {

  @NotNull
  private Long id;

  @NotBlank
  private String authority;

  public static AuthorityDTO of(Long id, String authority) {
    return new AuthorityDTO(id, authority);
  }
}
