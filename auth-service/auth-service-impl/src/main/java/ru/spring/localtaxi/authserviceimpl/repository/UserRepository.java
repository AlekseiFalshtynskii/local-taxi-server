package ru.spring.localtaxi.authserviceimpl.repository;//package ru.spring.localtaxi.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spring.localtaxi.authserviceimpl.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  Optional<User> findByEmail(String email);
}
