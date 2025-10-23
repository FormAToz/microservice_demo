
# Microservice demo

Demo of microservice interaction.
## Stack

* Java 17
* Spring Boot
* Lombok
* Mapstruct
* PostgreSQL
* Keycloak
* Kafka
* Zookeeper
* Redis

## How to use
**Clone repository from GitHub:**
```bash
$ git clone https://github.com/FormAToz/microservice_demo.git
```
FYI: all services have ReadMe-file with named commands.

**Create Docker images for all services (run commands inside each service project folder):**
```bash
# create new package
$ mvn clean package

# build named Docker image
$ docker build . -t <service-name>
```

**Create images from DockerHub:**
```bash
$ docker pull postgres
$ docker pull wurstmeister/kafka:latest
$ docker pull wurstmeister/zookeeper:latest
$ docker pull redis:alpine
```

**Create Docker image for Keycloak:**
```bash
# pull image from DockerHub
$ docker pull quay.io/keycloak/keycloak:latest

# run the container with Admin rights
$ docker run -d -p 8080:8080 -e kc_bootstrap_admin_username=admin -e kc_bootstrap_admin_password=admin quay.io/keycloak/keycloak:latest start-dev --http-port=8080

# access the Keycloak Admin Console
$ http://localhost:8080

# create realm
Manage realms -> Create realm -> format_realm

# create client
General settings:
	Client ID: fstock
	Client type: OpenID Connect
Capability config:
	Client authentication: enabled
	Standard flow: enabled
	Direct access grants: enabled
	Service accounts roles: enabled
Login settings:
	Valid redirect URIs: http://localhost/80*
	Web origins: *
	
# the client's secret key (required to receive the token) is located in
Clients -> fstock -> Credentials -> Client Secret

# create roles: ADMIN and USER
Clients -> fstock -> Roles -> Create role

# create realm roles and associate them with client roles
Role name: fstock-admin
Associated roles -> Assign role -> Client roles -> ADMIN
Role name: fstock-user
Associated roles -> Assign role -> Client roles -> USER

# create two users with different roles
Users -> Create new user:
    Email verified: enabled
    Username: admin
    Email: admin@email.com
    First name: admin
    Last name: admin
    Credentials -> Set password:
        Password: admin
        Temporary: disabled
    Role mapping -> Assign role -> Realm roles -> fstock-admin
    
    Email verified: enabled
    Username: user
    Email: user@email.com
    First name: user
    Last name: user
    Credentials -> Set password:
        Password: user
        Temporary: disabled
    Role mapping -> Assign role -> Realm roles -> fstock-user
    
# After receiving the token, you can view its contents and make sure that each user
# corresponds to his role. An example of getting a token for admin:
$ curl --location 'http://localhost:8080/realms/format-realm/protocol/openid-connect/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--header 'Authorization: Basic <Client Secret from step 2>' \
--data-urlencode 'grant_type=password' \
--data-urlencode 'username=admin' \
--data-urlencode 'password=admin'
```

**Get back to microservice-demo/dev project folder and run all services via Docker compose:**
```bash
$ cd microservice-demo/dev

$ docker-compose up -d

# get info about all running containers
$ docker ps
```
