# Java 17 사용 시
FROM openjdk:17-jdk

ARG JAR_FILE=route33-auth-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]