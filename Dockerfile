FROM openjdk:11.0.8-jre-buster

VOLUME /tmp
COPY build/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080