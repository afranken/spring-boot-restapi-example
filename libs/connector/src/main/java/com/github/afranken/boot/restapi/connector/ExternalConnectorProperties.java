package com.github.afranken.boot.restapi.connector;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("com.github.afranken.boot.restapi.connector")
public class ExternalConnectorProperties {
  /**
   * URI of the external API to call
   */
  @NotBlank
  private String externalApi = "https://api.github.com/repos/afranken/spring-boot-restapi-example";

  public String getExternalApi() {
    return externalApi;
  }

  public void setExternalApi(String externalApi) {
    this.externalApi = externalApi;
  }
}
