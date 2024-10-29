# Stage 1: Build the application with Gradle
FROM gradle:8.8-jdk17 AS build

WORKDIR /home/app

COPY backend/build.gradle /home/app/backend/
COPY backend/src /home/app/backend/src
COPY build.gradle settings.gradle gradle.properties /home/app/

RUN gradle -p /home/app clean build -x test

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-slim

COPY --from=build /home/app/backend/build/libs/*.jar /usr/local/lib/backend.jar

ENTRYPOINT ["java","-jar","/usr/local/lib/backend.jar"]
