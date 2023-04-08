FROM openjdk:11.0.8-jre-buster

EXPOSE 8080

COPY build/libs/currencyconverter-0.0.1-SNAPSHOT-plain.jar currency-converter-application.jar
ENTRYPOINT ["java","-jar","/currency-converter-application.jar"]