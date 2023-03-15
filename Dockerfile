# Build the .war file
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Deploy the .war
FROM tomcat:9.0.48-jdk11-openjdk-slim
COPY --from=build /home/app/target/hkadbus2.war /usr/local/tomcat/webapps/hkadbus2.war

# Setup environment variables
# Make sure the file exists and has at least the encrypter password with key ENCRYPTOR_PASSWORD
ADD config/setenv.sh /usr/local/tomcat/bin/setenv.sh

# Start the server
EXPOSE 8080
CMD ["catalina.sh", "run"]
