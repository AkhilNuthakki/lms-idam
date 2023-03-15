FROM openjdk:17
COPY target/lmsSignUp-0.0.1-SNAPSHOT.jar lmsSignUp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/lmsSignUp-0.0.1-SNAPSHOT.jar"]