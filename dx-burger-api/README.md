# 🍔 DX Burger API
By Anthony Vilarim Caliani

[![#](https://img.shields.io/badge/licence-MIT-blue.svg)](#) [![#](https://img.shields.io/badge/java-1.8-red.svg)](#) [![#](https://img.shields.io/badge/spring--boot-2.1.3.RELEASE-green.svg)](#) [![#](https://img.shields.io/badge/maven-3.6.0-blue.svg)](#) 

## Quick Start
Before you run this application you must have installed the following softwares in your machine:
 - Java 8
 - Maven
 - Docker

If everything is ok, then you just need to run `sh run.sh build-start` and enjoy it 🙂

## Project Map
```
dx-burger-api/
├── DockerFile
├── HELP.md
├── dx-burger-api.iml
├── mvnw
├── mvnw.cmd
├── nginx
│   ├── DockerFile
│   └── default.conf
├── pom.xml
├── run.sh
└── src
    ├── main
    │   ├── java
    │   │   └── br
    │   │       └── avcaliani
    │   │           └── dxburgerapi
    │   │               ├── DxBurgerApiApplication.java
    │   │               ├── common
    │   │               │   ├── Parser.java
    │   │               │   ├── Visitable.java
    │   │               │   └── Visitor.java
    │   │               ├── controller
    │   │               │   ├── BurgerController.java
    │   │               │   ├── IngredientController.java
    │   │               │   ├── OrderController.java
    │   │               │   └── common
    │   │               │       ├── AbstractController.java
    │   │               │       └── Response.java
    │   │               ├── domain
    │   │               │   ├── entity
    │   │               │   │   ├── Burger.java
    │   │               │   │   ├── BurgerIngredient.java
    │   │               │   │   ├── Ingredient.java
    │   │               │   │   ├── Order.java
    │   │               │   │   ├── OrderIngredient.java
    │   │               │   │   ├── OrderItem.java
    │   │               │   │   ├── Promotion.java
    │   │               │   │   └── User.java
    │   │               │   └── to
    │   │               │       ├── BurgerIngredientTO.java
    │   │               │       ├── BurgerTO.java
    │   │               │       ├── IngredientTO.java
    │   │               │       ├── OrderIngredientTO.java
    │   │               │       ├── OrderItemTO.java
    │   │               │       ├── OrderPriceTO.java
    │   │               │       ├── OrderTO.java
    │   │               │       └── UserTO.java
    │   │               ├── repository
    │   │               │   ├── BurgerIngredientRepository.java
    │   │               │   ├── BurgerRepository.java
    │   │               │   ├── IngredientRepository.java
    │   │               │   ├── OrderRepository.java
    │   │               │   ├── PromotionRepository.java
    │   │               │   └── UserRepository.java
    │   │               └── service
    │   │                   ├── BurgerService.java
    │   │                   ├── IngredientService.java
    │   │                   ├── OrderService.java
    │   │                   ├── PromotionService.java
    │   │                   ├── UserService.java
    │   │                   └── impl
    │   │                       ├── BurgerServiceImpl.java
    │   │                       ├── IngredientServiceImpl.java
    │   │                       ├── OrderServiceImpl.java
    │   │                       ├── PromotionServiceImpl.java
    │   │                       └── UserServiceImpl.java
    │   └── resources
    │       ├── application-dev.properties
    │       ├── application-prod.properties
    │       ├── application.properties
    │       ├── data.sql
    │       ├── static
    │       └── templates
    └── test
        └── java
            └── br
                └── avcaliani
                    └── dxburgerapi
                        ├── DxBurgerApiApplicationTests.java
                        └── service
                            ├── BurgerServiceTest.java
                            └── OrderServiceTest.java
```

## Help

These steps are only to support my development, you don't need to do this stuff.

```sh

# Base
mvn clean install
docker build -f DockerFile -t dx-burger-api .
cd nginx
docker build -f DockerFile -t dx-nginx .
docker run -d -p 8080:8080 --name dx-burger-api dx-burger-api
docker run -d -p 80:80 -p 443:443 --name dx-nginx --link=dx-burger-api dx-nginx

# Docker Containers
docker container ls -a
docker container rm $CONTAINER_ID
docker container stop $CONTAINER_ID

# Docker Images
docker images
docker images rm $IMAGE_ID

# Build & Run
docker build -f DockerFile -t $IMAGE_NAME .
docker run -p $HOST_PORT:$APP_PORT $IMAGE_NAME

# Nginx
docker run -d --name dx-nginx nginx
# Get inside Nginx container
bash -c "clear && docker exec -it nginx sh"

```

---

_You can find [@avcaliani](#) at [GitHub](https://github.com/avcaliani) or [GitLab](https://gitlab.com/avcaliani)._
