# Spring Boot REST API Example #

This example illustrates how to build a REST API application with [Spring Boot][1]

####Module explanation:

  `application/` this module is the application aggregator and produces the spring boot application

  `integrationtests/` this module contains the integration tests run against the `application`.

  `libs/` this module contains the features used by the application. There may be any number of libraries making up an application.

####Configuration:
Each library module exposes it's services through one or more configuration classes which are automatically loaded by spring boot because they are linked from `spring.factories`.

####Property overrides:
The properties in this project are structured as follows:

Each library may expose a set of properties that are imported by a properties class.
The property values are sensible defaults chosen by the library author that later may be overwritten.
See [Externalized Configuration][3] for more information on how to overwrite properties in Spring.


[1]:    http://projects.spring.io/spring-boot/                                "Spring Boot"
[2]:    http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#beans-java-configuration-annotation                                "Configuration Annotation"
[3]:    http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html                                "Externalized Configuration"
