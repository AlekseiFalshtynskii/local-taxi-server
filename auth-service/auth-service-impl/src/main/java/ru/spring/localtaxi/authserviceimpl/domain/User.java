package ru.spring.localtaxi.authserviceimpl.domain;

import static javax.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.LazyCollectionOption.FALSE;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotBlank
  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @JsonIgnore
  @NotBlank
  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "enabled", nullable = false)
  private boolean enabled;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "user_id")
  @LazyCollection(value = FALSE)
  private Set<Authority> authorities;

  @NotBlank
  @Email
  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @NotBlank
  @Column(name = "firstname", nullable = false)
  private String firstName;

  @Column(name = "lastname")
  private String lastName;

  @Column(name = "middlename")
  private String middleName;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "car_id")
  @LazyCollection(value = FALSE)
  private Car car;

  @Version
  private Long version;

  public static User of(String username, String password, String email, String firstName,
      String lastName, String middleName, Car car) {
    return new User(null, username, password, true, null, email, firstName, lastName, middleName,
        car, null);
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
}
