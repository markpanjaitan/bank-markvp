# Bank Balance

```shell
https://github.com/markpanjaitan/bank-markvp.git
```
######bank-markvp is an app that streams the customer's bank balance & transaction.

## Pre-Requisites
1. Java 15
2. MySQL Database
3. Maven
4. Docker
5. Redis
6. IDE : Eclipse / IntelliJ IDEA

## Technologies used
* Spring-boot
* MySQL
* Hibernate
* Spring Kafka Streams
* Spring Redis Cache
* Docker-compose
* Lombok

## Running the project

### 1. Starting Docker
```shell
docker compose -f ./docker-compose.yml up
```
### 2. Start the Main Application
```shell
mvnw compile exec:java -Dexec.mainClass="com.bank.markvp.BankMarkvpApplication"
```
### 3. Start the Transaction Producer Application
```shell
mvnw compile exec:java -Dexec.mainClass="com.bank.markvp.BankTransactionProducer"
```

## Guides :
REST API : Get balance from local database

```
http://localhost:8080/bank-balance/local/1
```
REST API : Get balance from state-store (Kafka)

```
http://localhost:8080/bank-balance/store/1
```

* The value from state-store will be different before and after using Transaction Producer.

* The API will not query from database if using same parameter (cacheable).
