package ru.spring.localtaxi.authserviceapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.spring.localtaxi.authserviceapi.enums.Authorities;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

  @NotNull
  private Long id;

  @NotBlank
  private String username;

  @JsonIgnore
  @NotBlank
  private String password;

  private boolean enabled;

  private Set<AuthorityDTO> authorities;

  @NotBlank
  @Email
  private String email;

  @NotBlank
  private String firstName;

  private String lastName;

  private String middleName;

  private CarDTO car;

  public static UserDTO of(Long id, String username, String password, boolean enabled,
      Set<AuthorityDTO> authorities, String email, String firstName, String lastName,
      String middleName, CarDTO car) {
    return new UserDTO(id, username, password, enabled, authorities, email, firstName, lastName,
        middleName, car);
  }

  public boolean isDriver() {
    return getAuthorities().stream()
        .filter(authority -> Authorities.AUTHORITY_DRIVER.name().equals(authority.getAuthority()))
        .count() == 1;
  }

  public boolean isPassenger() {
    return getAuthorities().stream()
        .filter(authority -> Authorities.AUTHORITY_PASSENGER.name().equals(authority.getAuthority()))
        .count() == 1;
  }
}
