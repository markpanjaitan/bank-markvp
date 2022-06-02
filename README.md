# Bank Balance

```shell
https://github.com/markpanjaitan/bank-markvp.git
```
bank-markvp is an app that holds the customer's bank balances.

## Running the project

### 1. Starting Kafka
```shell
docker compose -f ./docker-compose.yml up
```
### 2. Building and starting the application
```shell
./mvnw compile exec:java -Dexec.mainClass="com.bank.markvp.BankMarkvpApplication"
```