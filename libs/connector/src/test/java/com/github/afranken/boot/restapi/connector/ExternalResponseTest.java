package com.github.afranken.boot.restapi.connector;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.ObjectContent;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@JsonTest
public class ExternalResponseTest {

  @Autowired
  private ResourceLoader resourceLoader;

  @Autowired
  private JacksonTester<ExternalResponse> json;

  @Test
  public void deserialize_OK() throws IOException {
    final Resource resource = resourceLoader.getResource(
        "classpath:/com/github/afranken/boot/restapi/connector/githubResponse_OK.json");
    final ObjectContent<ExternalResponse> read = json.read(resource);
    assertThat(read).isEqualTo(new ExternalResponse("spring-boot-restapi-example",
        new ExternalResponse.Owner("afranken")));
  }

  @SpringBootConfiguration
  static class LocalConfig {
    //dummy configuration needed since there is no @SpringBootConfiguration for this module.
  }
}
