FROM openjdk:16
EXPOSE 8080
ADD target/spring-boot-training-docker.jar spring-boot-training-docker.jar
ENTRYPOINT ["java", "-jar", "/spring-boot-training-docker.jar"]