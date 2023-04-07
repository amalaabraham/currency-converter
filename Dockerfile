FROM openjdk:11.0.8-jre-buster

EXPOSE 8080

ARG JAR_FILE=build/libs/app.jar
COPY ${JAR_FILE} currency-converter-application.jar

ENTRYPOINT [ "sh", "-c", "java -Dserver.port=8080 -Dlogging.level.root=INFO -jar currency-converter-application.jar" ]