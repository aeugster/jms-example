= JMS Example =

== Setup (Starten der Anwendung) ==

=== docker & postgre ===
* install docker desktop e.g. 4.97.0
```
docker-compose up
```

=== app ==
* requires java 21 and maven
* database: TDB
* build & start with IntelliJ or other IDE

== Testing ==
* create order and watch processing
  GET on http://localhost:8080/api/orders/create

== Assumptions (Annahmen) ==
* Apache ActiveMQ 'Classic' looks like a good choice for a JMS broker.

== Technical decisions (Technische Entscheidungen) ==
* packages consumerapp and webapp allow separate artifacts and deployment

== Hints for productive usage (Hinweise für produktiven Einsatz) ==
* avoid in-memory queue?
* add mvc or unit tests
* avoid real passwords in .env file
* database: string status enum might be slow: use index or ordinal?
* use latest/r19 postgres and fix "pg_ctlcluster", details see https://github.com/docker-library/postgres/pull/1259


== Sources ==
* docker https://github.com/gaelgthomas/docker-compose-with-postgresql
