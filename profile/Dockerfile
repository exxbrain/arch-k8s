FROM openjdk:11-jre-slim-buster

ARG VERSION
ENV APP_VERSION=$VERSION

COPY build/libs/*.jar profile.jar

CMD java -jar profile.jar