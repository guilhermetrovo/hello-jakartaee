# hello-jakartaee
Over engineered "Hello World" application that uses:
* Java 21
* Jakarta EE 10
* Wildfly 30
* Arquillian and jUnit 4

The application is a simple hello world / echo HTTP endpoint, which context root deployed to `Wildfly` is by default at <http://localhost:8080/hello-jakartaee>.
The following endpoints are defined in [/src/main/java/HelloResource.java](https://github.com/guilhermetrovo/hello-jakartaee/blob/main/src/main/java/ca/trovo/hello/HelloResource.java) interface: 
- `/api/hello/ping` is a health-check dummy endpoint
- `/api/hello/say/{name}` is the hello / echo endpoint that will reply back a greetings along the provided parameter `{name}`

Currently there's a single `text/plain` implementation of these endpoints.

***

# Testing
## Maven
### Local Wildfly
```
mvn clean install verify -Parq-managed
```
This will:
1. Cleanup any previous artifacts from `/target/` folder
2. Compile the application as specified in [pom.xml](https://github.com/guilhermetrovo/hello-jakartaee/blob/main/pom.xml) 
3. Execute **unit tests** 
4. Package the application into a `.war` file (also in [pom.xml](https://github.com/guilhermetrovo/hello-jakartaee/blob/main/pom.xml) )
5. Startup your local Wildfly instance, specified in [/src/test/resources/arquillian.xml](https://github.com/guilhermetrovo/hello-jakartaee/blob/main/src/test/resources/arquillian.xml)
6. Deploy the application to Wildfly
7. Execute **integration tests** 
8. Undeploy the application from Wildfly
9. Stop Wildfly

### Remote Wildfly
```
mvn clean install verify -Parq-remote
```
This will:
1. Cleanup any previous artifacts from `/target/` folder
2. Compile the application as specified in [pom.xml](https://github.com/guilhermetrovo/hello-jakartaee/blob/main/pom.xml) 
3. Execute **unit tests** 
4. Package the application into a `.war` file (also in [pom.xml](https://github.com/guilhermetrovo/hello-jakartaee/blob/main/pom.xml) )
5. Deploy the application to the remote Wildfly instance, specified in [/src/test/resources/arquillian.xml](https://github.com/guilhermetrovo/hello-jakartaee/blob/main/src/test/resources/arquillian.xml)
7. Execute **integration tests** 
8. Undeploy the application from the remove Wildfly

***

# Using
TODO - See test code
