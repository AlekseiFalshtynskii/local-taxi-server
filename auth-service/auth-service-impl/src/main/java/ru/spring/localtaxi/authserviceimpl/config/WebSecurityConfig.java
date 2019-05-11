package ru.spring.localtaxi.authserviceimpl.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean("userPasswordEncoder")
  PasswordEncoder userPasswordEncoder() {
    return new BCryptPasswordEncoder(4);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService)
        .passwordEncoder(userPasswordEncoder());
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers(
        "/favicon.ico",
        "/h2-console/**",
        "/api/auth/signup",
        "/**"
    );
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors()
        .and()
        .csrf().disable()
        .headers().frameOptions().disable()
        .and()
        .authorizeRequests()
        .antMatchers("/login", "/oauth/token").permitAll();
  }
}
