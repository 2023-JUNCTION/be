FROM eclipse-temurin:17-jdk-alpine AS BUILDER

RUN mkdir /app_source
COPY . /app_source

WORKDIR /app_source

RUN chmod +x ./gradlew
RUN ./gradlew :api:bootJar

FROM eclipse-temurin:17-jdk-alpine AS RUNNER

RUN mkdir /app

COPY --from=BUILDER /app_source/api/build/libs /app

WORKDIR /app

ENV TZ=Europe/Helsinki

EXPOSE 8080
USER nobody

ARG DB_HOST
ARG DB_PASSWORD

ENV ENV_DB_HOST=${DB_HOST}
ENV ENV_DB_PASSWORD=${DB_PASSWORD}

ENTRYPOINT java -jar \
  -Dspring.profiles.active=${ENV_PHASE:-sandbox} \
  -Dbol.db.host=${ENV_DB_HOST} \
  -Dbol.db.password=${ENV_DB_PASSWORD} \
  /app/*.jar
