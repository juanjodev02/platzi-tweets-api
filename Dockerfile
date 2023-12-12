FROM gradle:7.4.1 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

FROM openjdk:21-jdk-slim
ENV PORT=80
EXPOSE $PORT
COPY --from=build /home/gradle/src/build/libs/platzi-tweets-0.0.1-SNAPSHOT.jar /app/
RUN bash -c 'touch /app/platzi-tweets-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-jar","/app/platzi-tweets-0.0.1-SNAPSHOT.jar"]