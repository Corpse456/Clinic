FROM java:8
FROM maven:alpine

WORKDIR /app/backend
ADD /backend/pom.xml /app/backend
ADD pom.xml /app
RUN mvn verify clean --fail-never

COPY . /app
RUN mvn clean install -DskipTests

RUN mvn spring-boot:run


