# Introduction

The Dropwizard countries application was developed to provide examples and some of the best practices I've been learning so far in Dropwizard.

# Running The Application

To test the example application run the following commands.

* To package the example run.

        mvn package

* To setup the h2 database run.

        java -jar target/dw-countries-1.0-SNAPSHOT.jar db migrate config.yml

* To run the server run.

        java -jar target/dw-countries-1.0-SNAPSHOT.jar server config.yml
