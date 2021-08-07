# Use the official gradle image to create a build artifact.
FROM gradle:6.7 as builder

# Copy local code to the container image.
COPY build.gradle.kts .
COPY gradle.properties .
COPY src ./src

# Build a release artifact.
RUN gradle installDist

FROM openjdk:8-jdk
EXPOSE 8080:8080
RUN mkdir /app
COPY --from=builder /home/gradle/build/install/gradle /app/
WORKDIR /app/bin
CMD ["./gradle"]


## https://docs.docker.com/develop/develop-images/multistage-build/#use-multi-stage-builds
# RUN gradle clean build --no-daemon
# FROM openjdk:8-jre-alpine
# COPY --from=builder /home/gradle/build/libs/gradle.jar /helloworld.jar
# CMD [ "java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "/helloworld.jar" ]
