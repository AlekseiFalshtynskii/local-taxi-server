package ru.spring.localtaxi.authserviceimpl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@EnableResourceServer
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.resourceId("localtaxi");
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.headers().frameOptions().disable()
        .and()
        .authorizeRequests()
        .antMatchers("/h2-console/**").permitAll()
        .antMatchers("/api/auth/**").permitAll()
        .antMatchers(HttpMethod.GET, "/api/users/**").permitAll()
        .anyRequest().authenticated();
  }
}
