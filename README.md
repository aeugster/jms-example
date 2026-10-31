# JMS Example

## Setup (Starten der Anwendung)

### docker & postgres
* install docker desktop e.g. 4.97.0
* startup creates a folder 'db-data' in the project root for postgres data
```
docker-compose up
```

### app
* requires java 21 and maven
* build & start with IntelliJ or other IDE

## Run and test
### fast testing and helpers (remove on prod)
* create order and watch processing
  GET on http://localhost:8080/orders/create
* test error handling 
  GET on http://localhost:8080/orders/create-missing
### curl commands
```
curl -X POST http://localhost:8080/orders -H "Content-Type: application/json" -d '{"customerId":"12345","amount":100.00}'
```

## Assumptions (Annahmen)
* Apache ActiveMQ 'Classic' looks like a good choice for a JMS broker.

## Technical decisions (Technische Entscheidungen)
* packages consumerapp and webapp allow separate artifacts and deployment
* consumer has dependencies on web

## Hints for productive usage (Hinweise für produktiven Einsatz)
* avoid in-memory queue
* add mvc or unit tests
* remove .env file from git
* database: OrderStatus as string might be slower than ordinal
* use latest/r19 postgres and fix "pg_ctlcluster", details see https://github.com/docker-library/postgres/pull/1259
* open telemetry example (unverified): https://spring.io/blog/2025/11/18/opentelemetry-with-spring-boot
* health, actuator and metrics use default spring boot libs

## Dead letter
* try re-feeding 2 hours later
* add better validations in controller
* enforce field email and send error message to responsible person (after human check)

## Sources
* docker https://github.com/gaelgthomas/docker-compose-with-postgresql
* jms https://medium.com/@balmikprajapati53/integration-of-activemq-with-spring-boot-3-3-x-on-jdk-17-a-complete-guide-32fd2281df9d
