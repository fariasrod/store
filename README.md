# Store Api

The project is developed respecting basic **API-FIRST** and **DDD** concepts and uses **liquibase** to manage and apply database schema changes.

Has 2 modules:
-  **app**: the API.
-  **spec**: the specification that would be the contract.

### Dependencies
- [Java 11 Development Kit](https://www.oracle.com/es/java/technologies/javase/jdk11-archive-downloads.html)
- [Lombok](https://projectlombok.org/)

### Built With
- [Java 11 Development Kit](https://www.oracle.com/es/java/technologies/javase/jdk11-archive-downloads.html)
- [H2](https://www.h2database.com/html/main.html)
- [Springboot 2.6.7](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Maven](https://maven.apache.org)
- [OpenApi](https://www.openapis.org)
- [Liquibase](https://www.liquibase.org/)
- [Mapstruct](https://mapstruct.org/)

### Concepts
- [Domain-Driven Design](https://www.amazon.es/Domain-Driven-Design-Tackling-Complexity-Software/dp/0321125215)
- [API-FIRST]()

### Getting Started
The application can be started:
- Using Maven, it is very important that we first make a `mvn clean install` in the Spec module then `mvn spring-boot:run` in the App.

### Useful information
- Port - 8080
- H2 - http://localhost:8080/h2-console

### Tests information
Unit, Integrated and Postman tests were developed.

### Request example
```sh
    curl --location 'http://localhost:8080/prices' \
    --header 'accept: application/json' \
    --header 'Content-Type: application/json' \
    --data '{
    "date": "2020-06-14T16:00:00Z",
    "brandId": 1,
    "productId": 35455
    }'
```
