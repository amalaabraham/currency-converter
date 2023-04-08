FROM openjdk:11.0.8-jre-buster

EXPOSE 8080

COPY build/libs/app.jar currency-converter-application.jar
ENTRYPOINT ["java","-jar","/currency-converter-application.jar"]