# Banking-System

Task-Scheduler is a lightweight application built to auto-schedule your daily/weekly/monthly/yearly routine with the motto of "Time Management? -> piece of cake".

  - built with **django** and **mysql**
  - no external dependencies
  - **Api** extensions available
  - **CLI** for terminal lovers:)

### Installation

Banking-System requires [Java](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) 1.8+ and Postgre SQL server / client to run.

Clone this repository.

```sh
$ git clone https://github.com/nirabhratapaswi/Banking-System.git
$ cd Banking-System
$ java -jar ./target/csminor.jar
(OR)
$ mvn spring-boot:run
(For Spring users:))
```

The above commands are to run the application only after Postgre SQL setup is done, so hold on!

For production environments... (*coming soon*)

```sh
$ 
```

### Setting up Database, and Testing

Banking-System assumes you have postgre sql installed and set up. We need to create a new user and database.

Install postgres
```sh
(For RPMs)
$ sudo yum install postgresql-server
(OR - for Ubuntu)
$ sudo apt-get update
$ sudo apt install postgresql postgresql-contrib
```

Switch to postgres server
```sh
$ sudo -i -u postgres
```

Create a new user (not Super-user), with passwords in interactive mode
```sh
$ createuser -P -S -e <user>
```

Log into postgres shell, create a database, update GRANT PRIVELEGES to user and connect to database
```sh
$ psql
$ CREATE DATABASE banking;
$ GRANT ALL PRIVILEGES ON banking to <user>;
$ \c banking;
```

### Starting the Banking-System

Continuing in shell
```sh
$ java -jar ./target/csminor.jar
(OR)
$ mvn spring-boot:run
```

**Free Distribution!**
