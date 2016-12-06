package com.github.afranken.boot.restapi.resource;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@JsonTest
public class ResourceResponseTest {
  @Autowired
  private JacksonTester<ResourceResponse> json;

  @Test
  public void deserialize_OK() throws IOException {
    final ObjectContent<ResourceResponse> read = json.read("deserialize_OK.json");
    assertThat(read)
        .isEqualTo(new ResourceResponse("spring-boot-restapi-example", "afranken", 13L));
  }

  @Test
  public void serialize_OK() throws IOException {
    final ResourceResponse response =
        new ResourceResponse("spring-boot-restapi-example", "afranken", 1L);

    final JsonContent<ResourceResponse> write = json.write(response);
    assertThat(write).isStrictlyEqualToJson("serialize_OK.json");
  }

  @SpringBootConfiguration
  static class LocalConfig {
    //dummy configuration needed since there is no @SpringBootConfiguration for this module.
  }
}
