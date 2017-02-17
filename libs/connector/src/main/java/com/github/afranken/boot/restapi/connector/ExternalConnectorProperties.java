package com.github.afranken.boot.restapi.connector;

import java.net.URI;
import javax.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.util.UriComponentsBuilder;

@Validated
@ConfigurationProperties("com.github.afranken.boot.restapi.connector")
class ExternalConnectorProperties {
  /**
   * URI of the external API to call
   */
  @NotNull
  private URI externalApiUri = UriComponentsBuilder
      .fromUriString("https://api.github.com/repos/afranken/spring-boot-restapi-example")
      .build()
      .toUri();

  public URI getExternalApiUri() {
    return externalApiUri;
  }

  public void setExternalApiUri(URI externalApiUri) {
    this.externalApiUri = externalApiUri;
  }
}
