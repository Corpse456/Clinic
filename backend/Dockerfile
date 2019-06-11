FROM java:8
FROM maven:alpine

WORKDIR /app/backend
ADD /backend/pom.xml /app/backend
ADD pom.xml /app

COPY ./backend/ /app
RUN mvn clean install -DskipTests


