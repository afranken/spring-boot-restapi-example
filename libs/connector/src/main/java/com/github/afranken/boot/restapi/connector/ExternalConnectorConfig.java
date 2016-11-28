package com.github.afranken.boot.restapi.connector;

import static org.apache.http.conn.params.ConnManagerParams.DEFAULT_MAX_TOTAL_CONNECTIONS;
import static org.apache.http.conn.params.ConnPerRouteBean.DEFAULT_MAX_CONNECTIONS_PER_ROUTE;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties
@Import(ExternalConnectorProperties.class)
public class ExternalConnectorConfig {
  private final ExternalConnectorProperties properties;
  private final RestTemplateBuilder restTemplateBuilder;

  @Autowired
  public ExternalConnectorConfig(final ExternalConnectorProperties properties,
      final RestTemplateBuilder restTemplateBuilder) {
    this.properties = properties;
    this.restTemplateBuilder = restTemplateBuilder;
  }

  @Bean
  public ExternalConnector externalConnector() {
    return new ExternalConnector(restTemplate(), properties);
  }

  private RestTemplate restTemplate() {
    return restTemplateBuilder
        .requestFactory(httpRequestFactory())
        .build();
  }

  private ClientHttpRequestFactory httpRequestFactory() {
    return new HttpComponentsClientHttpRequestFactory(httpClient());
  }

  private HttpClient httpClient() {
    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();

    connectionManager.setMaxTotal(DEFAULT_MAX_TOTAL_CONNECTIONS);
    connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_CONNECTIONS_PER_ROUTE);

    return HttpClients.custom().setConnectionManager(connectionManager).build();
  }
}
