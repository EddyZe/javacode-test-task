FROM openjdk:17

COPY target/javacode-test-task-0.0.1-SNAPSHOT.jar javacode-test-task-0.0.1-SNAPSHOT.jar

ENV TZ=Europe/Moscow
RUN cp /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "javacode-test-task-0.0.1-SNAPSHOT.jar"]