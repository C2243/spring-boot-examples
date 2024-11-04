## Spring Boot Example

This is an example Spring Boot app using Java.

### Prerequisites
* Java 21
* Gradle 7.5+

### Setup Spring boot dependency

#### /build.gradle
```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.2.5'
}

group = 'org.example'
version = '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    testImplementation platform('org.junit:junit-bom:5.10.0')
    testImplementation 'org.junit.jupiter:junit-jupiter'
    implementation 'org.springframework.boot:spring-boot-starter-web:3.3.5'
}

test {
    useJUnitPlatform()
}
```

* Add spring boot starter web into dependencies.
```groovy
implementation 'org.springframework.boot:spring-boot-starter-web:3.3.5'
```

* Add spring framework into plugins for building jar.
```groovy
id 'org.springframework.boot' version '3.2.5'
```

### Main Application
#### /src/main/java/com/example/Application.java
```java
package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

* <span style="color: yellow">SpringApplication</span> is a utility class that helps launch a Spring Boot application.
* <span style="color: yellow">@SpringBootApplication</span> is an annotation that indicates this class is a Spring Boot application configuration.

* <span style="color: yellow">SpringApplication.run()</span> takes two arguments:
  1. Application.class: Spring Boot uses current class (Application) to configure and launch the application.
  2. args: An array of strings representing command-line arguments passed to the application.

### Controller
```java
package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}

```
* <span style="color: yellow">@GetMapping</span> is an annotation that indicates a method can handle HTTP GET requests to a specific endpoint. <span style="color: yellow">@GetMapping("/hello")</span>, which means it can be accessed through an HTTP GET request to the /hello endpoint.

* <span style="color: yellow">@RestController</span> is an annotation that indicates this class is a RESTful web service controller.

### Build Jar

```shell
./gradlew bootJar
```
* <span style="color: yellow">bootJar</span>: This is an executable task in the Gradle wrapper script that builds an executable JAR file for your Spring Boot application.

### Run Application

```shell
java -jar ./build/libs/spring-boot-helloworld-1.0-SNAPSHOT.jar
```

### Test Application

```shell
curl http://localhost:8080/hello
```
* Default port should be 8080 for SpringBootApplication
* Response should be "Hello World"