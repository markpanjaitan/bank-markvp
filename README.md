# Bank Balance

```shell
https://github.com/markpanjaitan/bank-markvp.git
```
######bank-markvp is an app that holds the customer's bank balances, using : Spring Boot, REST-API, Kafka, Hibernate, Docker-Compose

## Pre-Requisites

1. Java 15
2. MySQL Database
3. Docker

## Running the project

### 1. Starting Kafka
```shell
docker compose -f ./docker-compose.yml up
```
### 2. Building and starting the application
```shell
./mvnw compile exec:java -Dexec.mainClass="com.bank.markvp.BankMarkvpApplication"
```