package com.github.afranken.boot.restapi.resource;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.afranken.boot.restapi.connector.ExternalConnector;
import com.github.afranken.boot.restapi.connector.ExternalResponse;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ResourceConfig.class)
public class ResourceTest {
  @Autowired
  private MockMvc mvc;

  @Autowired
  private ResourceLoader resourceLoader;

  @MockBean
  private ExternalConnector connector;

  @Test
  public void testSuccessResponse() throws Exception {
    final org.springframework.core.io.Resource response =
        resourceLoader.getResource("classpath:/com/github/afranken/boot/restapi/resource/response_OK.json");
    final String responseString = IOUtils.toString(response.getInputStream());

    when(connector.connect())
        .thenReturn(new ExternalResponse("testName",
            new ExternalResponse.Owner("testOwner"), 1L));

    mvc.perform(get("/gitinfo"))
        .andExpect(status().isOk())
        .andExpect(content().json(responseString));
  }

  @Import(ResourceConfig.class)
  @SpringBootConfiguration
  static class LocalConfig {
    //dummy configuration needed since there is no @SpringBootConfiguration for this module.
  }
}
