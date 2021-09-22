# SwaggerUI Package

This package contains the `SwaggerConfig` as a standalone configuration object. In Spring Boot the swagger documentation
is created through the springfox library. Additional types as well as data classes or controller model definitions must
be handcrafted.

## Components

1. ``SwaggerConfig`` contains the Docket bean object which is managed by spring.
    1. Docket is a builder which is intended to be the primary interface into the swagger-springmvc framework. Provides
       sensible defaults and convenience methods for configuration.
    2.
   Docs: http://springfox.github.io/springfox/javadoc/2.7.0/index.html?springfox/documentation/spring/web/plugins/Docket.html

