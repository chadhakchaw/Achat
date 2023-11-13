FROM openjdk:11-jre-slim

COPY target/achat-1.0.jar .
EXPOSE 8089
ENV IMAGE_NAME="ahmedmezni-5bi3-achat"
ENTRYPOINT ["java", "-jar", "achat-1.0.jar"]