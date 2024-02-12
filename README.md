## Role-based Access Control in Spring Boot 3 with JWT Implementation
- Sample Project for Spring Security Role-based Authorization.
- This project illustrates the implementation of role-based authorization and JWT authentication in Spring Boot 3.0 and Spring Security.

## Features
- User registration and login with JWT authentication.
- Role-based authorization with Spring Security.
- Logout mechanism.
- Customized exception handling
- OpenApi documentation.


## Technologies
- Java 17
- Spring 6.0.11
- Spring Boot 3.1.2
- Spring Batch 3.1.2
- PostgreSQL 42.6.0
- Docker 24.0.4

## How to build
- Clone the repository: git clone ```https://github.com/behrouztakhti/spring-security.git```
- You can build the project by running ```mvn clean package```.

## How to run
- 1 ) Navigate to the project directory: cd spring-security
- 2 ) We need **Docker** to run containers for **PostgreSQL** and **pgAdmin**;
you can skip it if PostgreSQL is installed on your computer.
Run the command below to start the Docker containers from the **docker-compose.yml** file(which is located in the root of project):

    ```agsl
    docker compose --file=docker-compose.yml up -d
    ```
    After ensuring that the PostgreSQL and pgAdmin containers are running,
    browse pgAdmin address then
    **Add database "security-sample" to PostgreSQL.**
- 3 ) Run the project: mvn spring-boot:run.

    Once the application runs you should see something like this:
```agsl
: Tomcat started on port(s): 3030 (http) with context path ''
: Started LaunchApplication in 11.681 seconds (process running for 12.723)
: spring-security application started successfully !
```
## To view swagger API docs
Run the server and browse to http://localhost:3030/swagger-ui/index.html
![swagger](https://github.com/behrouztakhti/spring-security/assets/6881159/3499be04-c0f6-46f4-839a-9426b99d2622)


## Schema



