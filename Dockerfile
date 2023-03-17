# Build the .war file
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Deploy the .war
FROM tomcat:9.0.48-jdk11-openjdk-slim
COPY --from=build /home/app/target/hkadbus2.war /usr/local/tomcat/webapps/hkadbus2.war

# Start the server
EXPOSE 8080
CMD ["catalina.sh", "run"]
