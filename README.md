# hello-jakartaee
Over engineered "Hello World" application that uses:
* Java 21
* Jakarta EE 10
* Wildfly 30
* Arquillian and jUnit 4

# Testing
## Running with Maven locally
```
mvn clean install verify -Parq-managed
```
This will:
1. Cleanup any previous artifacts from `/target/` folder
2. Compile the application as specified in `pom.xml`
3. Execute **unit tests** 
4. Package the application into a `.war` file (also in `pom.xml`)
5. Startup your local Wildfly instance, specified in `src/test/resources/arquillian.xml`
6. Deploy the application to Wildfly
7. Execute **integration tests** 
8. Undeploy the application from Wildfly
9. Stop Wildfly

## Running with Maven remote
```
mvn clean install verify -Parq-remote
```
This will:
1. Cleanup any previous artifacts from `/target/` folder
2. Compile the application as specified in `pom.xml`
3. Execute **unit tests** 
4. Package the application into a `.war` file (also in `pom.xml`)
5. Deploy the application to the remote Wildfly instance, specified in `src/test/resources/arquillian.xml`
7. Execute **integration tests** 
8. Undeploy the application from the remove Wildfly

# Using
TODO - See test code
