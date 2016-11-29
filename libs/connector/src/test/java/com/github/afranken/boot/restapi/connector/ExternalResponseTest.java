package com.github.afranken.boot.restapi.connector;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.afranken.boot.restapi.util.ValidatorUtil;
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
public class ExternalResponseTest {

  @Autowired
  private JacksonTester<ExternalResponse> json;

  @Test
  public void deserialize_OK() throws IOException {
    final ObjectContent<ExternalResponse> read = json.read("githubResponse_OK.json");
    assertThat(read).isEqualTo(new ExternalResponse("spring-boot-restapi-example",
        new ExternalResponse.Owner("afranken")));
    ValidatorUtil.validate(read.getObject());
  }

  @Test
  public void serialize_OK() throws IOException {
    final ExternalResponse response = new ExternalResponse("spring-boot-restapi-example",
        new ExternalResponse.Owner("afranken"), 0L);

    final JsonContent<ExternalResponse> write = json.write(response);
    assertThat(write).isStrictlyEqualToJson("serialize_OK.json");
  }

  @SpringBootConfiguration
  static class LocalConfig {
    //dummy configuration needed since there is no @SpringBootConfiguration for this module.
  }
}
