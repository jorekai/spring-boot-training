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

## Annotations - Springfox (swagger.annotations)

1. **@ApiImplicitParams**
    1. A wrapper to allow a list of multiple ApiImplicitParam objects.
2. **@ApiImplicitParam**
    1. Represents a single parameter in an API Operation.
    2. Defines the swagger documentation of the mapping's parameters
    3. Docs: https://docs.swagger.io/swagger-core/current/apidocs/io/swagger/annotations/ApiImplicitParam.html
3. **@Example**
    1. An optionally named list of example properties.
4. **@ExampleProperty**
    1. A mediaType/value property within a Swagger example
    2. Docs: https://docs.swagger.io/swagger-core/current/apidocs/io/swagger/annotations/ExampleProperty.html
5. **@ApiModelProperty**
    1. Define the conventional representation of a DTO model or DB model for swagger ui
    2. E.g. ``@ApiModelProperty(dataType = "[Ljava.lang.String;")``
    3. Docs: https://docs.swagger.io/swagger-core/current/apidocs/io/swagger/annotations/ApiModelProperty.html