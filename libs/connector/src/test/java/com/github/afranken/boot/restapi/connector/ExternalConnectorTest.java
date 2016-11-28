package com.github.afranken.boot.restapi.connector;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;

@RestClientTest
@Import(ExternalConnectorConfig.class)
@RunWith(SpringRunner.class)
public class ExternalConnectorTest {

  @Autowired
  private ExternalConnector connector;

  @Autowired
  private ResourceLoader resourceLoader;

  @Autowired
  private MockRestServiceServer server;

  @Before
  public void setup() {
    server.reset();
  }

  @Test
  public void testConnect_OK() {
    final Resource resource = resourceLoader.getResource(
        "classpath:/com/github/afranken/boot/restapi/connector/githubResponse_OK.json");
    server.expect(once(), requestTo("https://api.github.com/repos/afranken/spring-boot-restapi-example"))
        .andExpect(method(HttpMethod.GET))
        .andRespond(withSuccess(resource, MediaType.APPLICATION_JSON));
    final ExternalResponse response = connector.connect();
    assertThat(response.getName(), is("spring-boot-restapi-example"));
  }

  @Test(expected = HttpClientErrorException.class)
  public void testConnect_BadRequest() {
    server.expect(once(), requestTo("https://api.github.com/repos/afranken/spring-boot-restapi-example"))
        .andExpect(method(HttpMethod.GET))
        .andRespond(withBadRequest());
    connector.connect();
  }

}
