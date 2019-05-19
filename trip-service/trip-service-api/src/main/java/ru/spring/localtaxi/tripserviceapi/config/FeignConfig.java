package ru.spring.localtaxi.tripserviceapi.config;

import feign.RequestInterceptor;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

public class FeignConfig {

  @Value("${security.oauth2.client.access-token-uri}")
  private String accessTokenUri;

  @Value("${security.oauth2.client.client-id}")
  private String clientId;

  @Value("${security.oauth2.client.client-secret}")
  private String clientSecret;

  @Value("#{'${security.oauth2.client.scope}'.split(',')}")
  private List<String> scope;

  @Bean
  public RequestInterceptor requestInterceptor(OAuth2ClientContext oAuth2ClientContext) {
    return new OAuth2FeignRequestInterceptor(oAuth2ClientContext, resource());
  }

  private OAuth2ProtectedResourceDetails resource() {
    ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
    details.setAccessTokenUri(accessTokenUri);
    details.setClientId(clientId);
    details.setClientSecret(clientSecret);
    details.setScope(scope);
    return details;
  }
}
