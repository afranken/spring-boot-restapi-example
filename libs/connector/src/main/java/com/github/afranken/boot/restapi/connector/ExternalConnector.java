package com.github.afranken.boot.restapi.connector;

import org.springframework.web.client.RestTemplate;

/**
 * This class encapsulates external connections.
 */
public class ExternalConnector {

  private final RestTemplate restTemplate;

  private final ExternalConnectorProperties properties;

  public ExternalConnector(final RestTemplate restTemplate,
      final ExternalConnectorProperties properties) {
    this.restTemplate = restTemplate;
    this.properties = properties;
  }

  public ExternalResponse connect() {
    return restTemplate.getForObject(properties.getExternalApi(), ExternalResponse.class);
  }
}
