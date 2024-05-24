FROM gradle:7.6.0-jdk17 AS build
WORKDIR /app
COPY build.gradle gradle.properties settings.gradle ./
COPY src ./src
RUN gradle build -x test --no-daemon

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar application.jar
COPY src/main/resources/questions.json /app/questions.json
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "application.jar"]