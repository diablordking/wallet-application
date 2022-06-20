
# Wallet Application

It's simple wallet microservice running on the JVM that manages credit/debit
transactions on behalf of players.

## Architecture of Wallet Application

In Wallet Application, I implement Hexagonal architecture. My main goal of this architecture is to avoid knows structural pitfalls in software design and creating loosely coupled components that can be connected to their software environments using “ports” and “adapters”  As a result, this makes components replaceable at any level and facilitates the testability of the single parts.

Application consists of three main projects

* registry-server (It's eureka server for microservices)
* wallet-microservice (It's microservice. Contains wallet-subdomain)
* wallet-loadbalancer (It's loadbalancer for microservice instances. it use round robin algorithm)

Database H2 and data persist across restarts.

## Requirements

    🔸 JDK 17   
    🔸 STS or Eclipse

## Running the Wallet Application
    - Create workspace in STS or Eclipse
    - Import the projects as a "existing maven projects" into workspace 
    - Do maven clean install all projects in order wallet-subdomain,wallet-microservice,wallet-loadbalancer,registry-server 
    - Run projects in order: registry-server,wallet-microservice,wallet-loadbalancer
    - You can create instance of wallet-microservice as much as you like for scalling.

 ## Swagger Api Documentation

    🔸 http://localhost:9090/doc/swagger-ui/swagger-ui/index.html


## For Production Environment

it needs Logging mechanism,Configuration server,Auto scalling, Dockerize all projects

### Writer

Mehmet Alp Albayraktaroğlu
