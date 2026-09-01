FROM amazoncorretto:21 AS build

ARG ARTEFACT_VERSION='1.0.0'
WORKDIR /home/app

COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY gradle/libs.versions.toml gradle/libs.versions.toml

RUN chmod +x gradlew && ./gradlew dependencies --no-daemon

COPY src src
RUN ./gradlew bootJar --no-daemon -PartefactVersion=${ARTEFACT_VERSION}

FROM amazoncorretto:21-alpine

WORKDIR /app

COPY --from=build /home/app/build/libs/helix-server-${ARTEFACT_VERSION}.jar app.jar

EXPOSE 8080

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]