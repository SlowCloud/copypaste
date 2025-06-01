FROM gradle:jdk17-alpine AS builder
WORKDIR /builder
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle .
COPY settings.gradle .
COPY src ./src
RUN ./gradlew bootJar --no-daemon

FROM gcr.io/distroless/java17-debian12:nonroot
WORKDIR /runner
COPY --from=builder /builder/build/libs/*.jar ./app.jar
ENTRYPOINT [ "/runner/app.jar" ]