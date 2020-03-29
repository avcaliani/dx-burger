# 🍔 DX Burger
By Anthony Vilarim Caliani

[![#](https://img.shields.io/badge/licence-MIT-blue.svg)](#) [![#](https://img.shields.io/badge/docker-18.09.2-steelblue.svg)](#)

These are my **DX Burger** Projects which contain a web application developed in Angular and a RESTful API developed in Java with Spring Boot. Both projects are prepared to run inside a Docker container and as a bonus the DX Burger API will run behind a Nginx server which will be running inside another Docker container.

## Projects
- **[dx-burger-api](dx-burger-api/README.md)**: This is the DX Burger RESTFul API.
- **[dx-burger-web](dx-burger-web/README.md)**: This is the DX Burger Web Application.

## How can you run it in your machine?
First of all, you need some softwares to make it possible.
 - ☕️ **API**: Java 1.8
 - 🍃 **API**: Maven
 - 😎 **WEB**: Node.js + npm
 - 🅰️ **WEB**: Ng CLI
 - 🐳 **BOTH**: Docker

So, let's do this! I've prepared 2 files that are called `run.sh`. One of them is for DX Burger API and the other one is for DX Burger Web. 

> If you want to know more about `run.sh` run `run.sh -h`

Now you just need to follow these steps:
```sh
# Open your terminal and run...
cd <PROJECT_DIR>/dx-burger-api
sh run.sh build-start

# This command should build the DX Burger API and execute its JUnit Tests.
# After that, it will create and run a DX Burger API docker image 
# and in the end it will do the same to Nginx docker image.
#
# Curiosity: Why do I need a Nginx docker image??
#            Because this Nginx server will be my HTTP Proxy, which means
#            that all requests to DX Burger API will be done to Nginx server
#            and it will redirect these requests to DX Burger API instead of 
#            directly accessing the API.


# Wait a little ;)
# When it finishes, you can continue...

cd ../dx-burger-web
npm install # ----------------> Only if you don't have "node_modules" folder.
sh run.sh build-start

# This command should build the DX Burger Web Application.
# After that, it will create and run a DX Burger Web docker image.

# Wait a little ;)
# When it finishes, open your browser at "http://localhost:3000" and enjoy it!

```
