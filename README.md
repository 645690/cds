# CDS

Web Application for a distribution system

# Setup

*   Port 8080
*   In-built maven
*   Able to run after importing

# Endpoints

*   /users - view users with salary between 0 to 4000 inclusively
*   /h2-console - default h2 UI
*   /swagger-ui - GUI tool for Restful services


## Built with

* 	[Maven](https://maven.apache.org/) - Dependency Management
* 	[JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit 
* 	[Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* 	[git](https://git-scm.com/) - Free and Open-Source distributed version control system 
* 	[Swagger](https://swagger.io/) - Open-Source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.
*   [OpenCSV](http://opencsv.sourceforge.net/) - CSV parser library for Java
*   [H2](https://www.h2database.com/html/main.html) - In-Memory Java SQL database

# Justifications

*   Spring Security disabled currently and would rather look into an API Manager like Kong to handle API endpoint securities.
*   Result json key hardcoded but on a larger scale would be a Response DTO.

