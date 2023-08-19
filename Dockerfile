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

ENV TZ=Asia/Seoul

EXPOSE 8080
USER nobody

ARG RDS_HOST
ARG RDS_USERNAME
ARG RDS_PW

ENV ENV_RDS_HOST=${RDS_HOST}
ENV ENV_RDS_USERNAME=${RDS_USERNAME}
ENV ENV_RDS_PW=${RDS_PW}

ENTRYPOINT java -jar \
  -Dspring.datasource.url=${ENV_RDS_HOST}
  -Dspring.datasource.username=${ENV_RDS_USERNAME}
  -Dspring.datasource.password=${ENV_RDS_PW}
  /app/*.jar
