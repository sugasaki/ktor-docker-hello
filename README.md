

# Step1  Creating Ktor Project

Create a new project in [Ktor Project Generator](https://start.ktor.io/)

note: You can also create a new project in IntelliJ, but it is deprecated because it is obsolete.

<img width="500" alt="68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f34393931302f32653166333036382d333033622d613933302d323162352d3134383732303532626530372e706e67" src="https://user-images.githubusercontent.com/1316886/128617297-ca7dc706-a758-4037-84a6-acfbcdc67c96.png">

# Step2 Creating Docker

## Dockerfile

Create a `Dockerfile` in the root folder

```:Dockerfile
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
```

### debug


```
docker build -t my-application .
docker run -p 8080:8080 my-application
```


## docker-compose.yml

Create `docker-compose.yml` in the root folder

```:docker-compose.yml
version: '2'
services:
  web:
    build:
      context: ./
      dockerfile: Dockerfile
    ports:
      - 8080:8080
```


# Step3 Docker Build & Run

```
docker-compose up --build
```

After booting, accessed `http://0.0.0.0:8080/` and saw Hello World!



<img width="486" alt="68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f34393931302f36373739383461652d366165632d373063622d636630642d3336663137653034303338352e706e67" src="https://user-images.githubusercontent.com/1316886/128617299-2aeadf83-e64e-4448-bf59-933dd9378e4e.png">



# Reference

[Kotlin > Ktor > DockerでHello world\! \- Qiita](https://qiita.com/sugasaki/items/d5800aedafc7dd3f528c)

### docker up

```
docker-compose up
 
 or 
 
docker-compose up --build
```


### down

```
docker-compose down
```

### into the docker

```
docker exec -i -t container--ktor-test bash
```



[docs/docs/serving/samples/hello\-world/helloworld\-kotlin at mkdocs · knative/docs](https://github.com/knative/docs/tree/mkdocs/docs/serving/samples/hello-world/helloworld-kotlin)

[Docker \| Ktor](https://ktor.io/docs/docker.html#getting-the-application-ready)



