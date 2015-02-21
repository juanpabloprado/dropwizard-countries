# Introduction

The Dropwizard countries application was developed to provide examples and some of the best practices I've been learning in Dropwizard so far.

# Running The Application

To test the countries application run the following commands.

* To package the example run.

        mvn package

* To setup the MySQL database run.

        java -jar target/dw-countries-1.0-SNAPSHOT.jar db migrate config.yml

* To run the server run.

        java -jar target/dw-countries-1.0-SNAPSHOT.jar server config.yml
