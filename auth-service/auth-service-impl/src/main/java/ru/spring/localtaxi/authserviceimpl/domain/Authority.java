package ru.spring.localtaxi.authserviceimpl.domain;//package ru.spring.localtaxi.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import ru.spring.localtaxi.authserviceimpl.enums.Authorities;

@Entity
@Table(name = "authorities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authority implements GrantedAuthority {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "authority", nullable = false)
  private String authority;

  public static Authority of(Authorities authority) {
    return new Authority(null, authority.name());
  }
}
