package com.github.afranken.integrationtest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import com.github.afranken.boot.restapi.Application;
import com.github.afranken.boot.restapi.resource.ResourceResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = Application.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class IntegrationTests {

  @Autowired
  private TestRestTemplate template;

  @Test
  public void testRequest() throws Exception {

    ResponseEntity<ResourceResponse> entity =
        template.getForEntity("/gitinfo", ResourceResponse.class);

    ResourceResponse body = entity.getBody();

    assertThat(body.getName(), containsString("spring-boot-restapi-example"));
    assertThat(body.getWatchers(), is(greaterThanOrEqualTo(0L)));
  }
}
