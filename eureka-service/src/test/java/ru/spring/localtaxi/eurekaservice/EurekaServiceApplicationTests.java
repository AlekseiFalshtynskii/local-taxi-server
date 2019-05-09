package ru.spring.localtaxi.eurekaservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = EurekaServiceApplication.class)
public class EurekaServiceApplicationTests {

  @Value("${local.server.port}")
  private int eurekaPort;

  @Test
  public void mainPage() {
    String html = new TestRestTemplate()
        .getForObject("http://localhost:" + eurekaPort, String.class);
    assertTrue(html.contains("<td>status</td><td>UP</td>"));
  }

  @Test
  public void catalogLoads() {
    ResponseEntity<Map> entity = new TestRestTemplate()
        .getForEntity("http://localhost:" + eurekaPort + "/eureka/apps", Map.class);
    assertEquals(HttpStatus.OK, entity.getStatusCode());
  }

  @Test
  public void adminLoads() {
    ResponseEntity<Map> entity = new TestRestTemplate()
        .getForEntity("http://localhost:" + eurekaPort + "/actuator/health", Map.class);
    assertEquals(HttpStatus.OK, entity.getStatusCode());
  }
}
