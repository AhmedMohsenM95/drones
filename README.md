# Drones Application
This project is for supporting the use of drones with the below features.

* Registering a drone
* Loading a drone with medication items
* Checking loaded medication items for a given drone
* Checking available drones for loading
* Check drone battery level for a given drone


## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

If you are running the project from an IDEA link intellij you have to enable annotation processing for `lombok` plugin

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.musala.drones.DronesApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Swagger UI (Swagger Documentation)

To access swagger UI is through this link after running the application:
[Swagger UI documentation](http://localhost:8080/swagger-ui/index.html)



## Request samples for testing through REST Client

* Registering a drone
 ``` 
POST http://localhost:8080/drone
  Content-Type: application/json

{
"serialNumber": "E12EAE24",
"model": "Lightweight",
"weightLimit": 300,
"batteryCapacity": 70,
"droneState": "IDLE"
}
```

* Loading a drone with medication items
 ``` 
POST http://localhost:8080/drone/E12EAE23/load
Content-Type: application/json

[
  {
    "name": "AHMED_2",
    "weight": 100,
    "code": "TEST_PNG",
    "image": {
        "data": "/9j/4AA/..", --base64
        "fileName": "test",
        "fileExtension": ".png"
      }

  }
]
```

* Checking loaded medication items for a given drone
 ```
GET http://localhost:8080/drone/E12EAE23/medications
```

* Checking available drones for loading
 ```
GET http://localhost:8080/drone/load/available
```

* Check drone battery level for a given drone
 ```
GET http://localhost:8080/drone/E12EAE24/battery
```
