# shorturl

A project to allow to shorten URLs into a 7 character short url. 

## Demo

https://github.com/Nidheesh-Panchal/shorturl/assets/33429560/e4ed051f-ef8c-4c0b-8d54-7faeb9ae97e5

## Future Work

Create a docker container for the backend server and host it on Cloud platform.

## Dev Notes

### How to Build the project

Prerequisites:

- Java 17
- maven

Run the following command to build the project. This will generate a `*.jar` file for the project.

```bash
# build project
mvn clean package

# A jar is created at the target/shorturl-0.0.1.jar
```

Using this jar file to build docker image.

```bash
docker build --no-cache -t short-url:v0.0.1 .

# Optionally push to a repository of your liking
```

### Run the app in docker

```bash
docker compose up -d
docker compose logs -f
```

### Run with kustomize on k8s cluster

```bash

# Build image for minikube local env
eval $(minikube docker-env)
docker build -t short-url:v0.0.1 .

# Run kustomize command to get a single yaml manifest file
# this will save the file to out.yaml in k8s folder
# easy to check what was actually deployed
cd k8s
kubectl kustomize ./ > out.yaml

# apply the manifest files
kubectl apply -k ./
```
