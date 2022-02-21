FROM openjdk:latest
COPY ./target/sem_employees-0.1.0.3-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "sem_employees-0.1.0.3-jar-with-dependencies.jar"]