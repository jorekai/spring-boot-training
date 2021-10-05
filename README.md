# spring-boot-training

**Resources to stumble upon**:

- JPA Persistence API Introduction
    - https://www.vogella.com/tutorials/JavaPersistenceAPI/article.html

# Structure

- ``src/main/java `` contains all java classes and the entry point for the SpringBoot Container
- ``src/main/resources`` contains all static resources and the application properties for Spring Boot Container
  Configuration
- ``src/main/test`` contains the testing modules for the test client
- ``target`` contains all build output of the Spring Boot Application
- ``db.Dockerfile`` specifies the entrypoint for a postgres docker container
- ``Dockerfile`` specifies the main Dockerfile for the Spring Boot entrypoint
- ``docker-compose.yml`` specifies the Compose Configuration for the development mode
- ``init.sql`` defines an initialization SQL script for postgres