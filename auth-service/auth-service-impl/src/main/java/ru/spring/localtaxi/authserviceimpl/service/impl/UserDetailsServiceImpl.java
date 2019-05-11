package ru.spring.localtaxi.authserviceimpl.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.spring.localtaxi.authserviceimpl.repository.UserRepository;

@Service
@Data
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username).orElseThrow(
        () -> new UsernameNotFoundException("Пользователь с логином '" + username + "' не найден"));
  }
}
