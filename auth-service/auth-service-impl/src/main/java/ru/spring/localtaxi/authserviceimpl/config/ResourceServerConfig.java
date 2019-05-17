package ru.spring.localtaxi.authserviceimpl.config;

import java.util.Arrays;
import java.util.Collections;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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

  @Bean
  public FilterRegistrationBean<CorsFilter> filterRegistrationBean() {
    FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
        new CorsFilter(corsConfigurationSource()));
    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
    return bean;
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Collections.singletonList("*"));
    configuration.setAllowedMethods(
        Arrays.asList("OPTIONS", "HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
    configuration.setAllowCredentials(true);
    configuration.setAllowedHeaders(Collections.singletonList("*"));
    configuration.setExposedHeaders(
        Arrays.asList("X-Auth-Token", "Authorization", "Access-Control-Allow-Origin",
            "Access-Control-Allow-Credentials"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
