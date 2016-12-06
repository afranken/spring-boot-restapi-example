package com.github.afranken.boot.restapi.resource;

import com.github.afranken.boot.restapi.connector.ExternalConnector;
import com.github.afranken.boot.restapi.connector.ExternalResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class Resource {
  private final ExternalConnector connector;

  Resource(ExternalConnector connector) {
    this.connector = connector;
  }

  @GetMapping("/gitinfo")
  ResourceResponse home() {
    final ExternalResponse response = connector.connect();

    return new ResourceResponse(response.getName(), response.getOwner().getLogin(),
        response.getWatchers());
  }
}
