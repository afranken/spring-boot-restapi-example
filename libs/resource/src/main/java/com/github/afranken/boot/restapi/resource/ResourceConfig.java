package com.github.afranken.boot.restapi.resource;

import com.github.afranken.boot.restapi.connector.ExternalConnector;
import com.github.afranken.boot.restapi.connector.ExternalConnectorConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ExternalConnectorConfig.class)
class ResourceConfig {
  private final ExternalConnector connector;

  @Autowired
  public ResourceConfig(final ExternalConnector connector) {
    this.connector = connector;
  }

  @Bean
  Resource resource() {
    return new Resource(connector);
  }

}
