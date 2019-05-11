package ru.spring.localtaxi.authserviceimpl.config;

import javax.sql.DataSource;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@AllArgsConstructor
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

  private final DataSource dataSource;

  private final AuthenticationManager authenticationManager;

  private final UserDetailsService userDetailsService;

  @Bean
  public TokenStore tokenStore() {
    return new JdbcTokenStore(dataSource);
  }

  @Bean("clientPasswordEncoder")
  public PasswordEncoder clientPasswordEncoder() {
    return new BCryptPasswordEncoder(8);
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer cfg) throws Exception {
    cfg.checkTokenAccess("permitAll")
        .passwordEncoder(clientPasswordEncoder());
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.jdbc(dataSource)
        .withClient("localtaxi")
        .secret(clientPasswordEncoder().encode("localtaxi@123"))
        .authorizedGrantTypes("password", "refresh_token")
        .scopes("read", "write")
        .accessTokenValiditySeconds(86400)
        .refreshTokenValiditySeconds(2592000)
        .and()
        .build();
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.tokenStore(tokenStore())
        .authenticationManager(authenticationManager)
        .userDetailsService(userDetailsService);
  }
}