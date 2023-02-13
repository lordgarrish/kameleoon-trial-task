FROM openjdk:18.0.2.1

EXPOSE 8080

RUN mkdir ./kameleoon_api

COPY ./target/kameleoon-trial-task-0.0.1-SNAPSHOT.jar ./kameleoon_api

ENTRYPOINT ["java", "-jar", "./kameleoon_api/kameleoon-trial-task-0.0.1-SNAPSHOT.jar"]