# beers_quarkus_gradle



This project can be found here: https://github.com/agerber/beers_quarkus_gradle


## RUN LOCALLY

Make sure docker desktop is running. Spin up a mongo db locally.

> docker run -d -it --rm  -p 27017:27017 mongo

run the beers_quarkus_gradle.main run config. 


## VIEW SWAGGER LOCALLY

http://localhost:8080/q/swagger-ui/


## VIEW MICROSERVICE LOCALLY

http://localhost:8080/beers


## BUILD INSTRUCTIONS
//from terminal, in root of this project, use gradle wrapper to clean build and exclude tests
> ./gradlew clean build -x test


//build a linux-image using the jvm Dockerfile. You must replace gerber2816 with your docker-hub-name
> docker build --platform linux/amd64 -f src/main/docker/Dockerfile.jvm -t wz1305/beers_quarkus_gradle-jvm .

## PUSH INSTRUCTIONS
//push to docker-hub. You must replace gerber2816 with your docker-hub-name
> docker push wz1305/beers_quarkus_gradle-jvm

## DEPLOY TO LIGHTSAIL INSTANCE

Log into lightsail. Select Instances on left menu. Select Linux/Unix || OS Only  || Amazon Linux 202X 
At minimum, select the $5 per month option (with 3 months free). Anything less will not work. 
Leave all other options as default, and click 'Create instance'

Click on the instance name.

Note the PUBLIC IP address and make a note of this: 54.147.236.105

Go to Networking || IPv4 Firewall || Add rule
Custom TCP 8080
click Create.



Click the elipses and Connect menu option. 
Once inside the linux terminal, switch to root user

> sudo su

> yum install docker -y

//if you need to stop it later, you can run: service docker stop
> service docker start

//check that it's running
> service docker status

> docker pull mongo:latest

//replace gerber2816 with your docker-hub-name
> docker pull wz1305/beers_quarkus_gradle-jvm

//confirm that you have both locally
> docker images

//run mongo in a docker container in a detached-state on host network
> docker run -d --net=host  -p 27017:27017 mongo


//run quarkus in a docker container in a detached-state on host network. replace gerber2816 with your docker-hub-name
> docker run -d --net=host  -p 8080:8080 wz1305/beers_quarkus_gradle-jvm

//confirm that both are running
> docker ps


From the noted IP address above
go to a browser and type: 54.147.236.105:8080/favorite

## DEPLOY TO LIGHTSAIL CONTAINER

See video on Deploying to Lightsail Container







## ORIGINAL README BELOW >>>>>>>>>>>>>>>>>>>>>>

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:

```shell script
./gradlew build
```

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./gradlew build -Dquarkus.package.type=native
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/beers_quarkus_gradle-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling.

## Related Guides

- SmallRye OpenAPI ([guide](https://quarkus.io/guides/openapi-swaggerui)): Document your REST APIs with OpenAPI - comes
  with Swagger UI
- Kotlin ([guide](https://quarkus.io/guides/kotlin)): Write your services in Kotlin

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
