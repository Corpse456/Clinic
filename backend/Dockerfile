FROM maven:3.6.0-jdk-11-slim AS build
COPY backend/src /home/app/backend/src
COPY backend/pom.xml /home/app/backend
COPY pom.xml /home/app
RUN mvn -f /home/app/backend/pom.xml clean package -DskipTests

FROM openjdk:8
COPY --from=build /home/app/backend/target/backend-0.0.1-SNAPSHOT.jar /usr/local/lib/backend.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/backend.jar"]
