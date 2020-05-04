# Detailed Information

### Prerequisites

* Download project from Git Hub site (URL provided by email).
* Maven installed
* JDK 8+ installed
* Connection to Zemoga's MySQL database

### Technologies

* Thymeleaf
* Spring Web
* Spring MVC
* Spring Data
* Spring Boot
* Spring Social Twitter
* Spring Boot Test
* JUnit
* MySQL Database

### Start Up

* Open shell or command prompt window.
* Make sure you're located under */.../Zemoga/project* directory.
* Compile, install, and run tests by doing: *sh mvnw clean install*
* Run Spring Boot server by doing: *sh mvnw spring-boot:run*

### Usage

* Get portfolio information by id through web browser:<br/>
http://localhost:8082/portfolio-web/portfolio?id=1

* Call endpoint to get portfolio information by id:<br/>
`curl --location --request GET 'http://localhost:8082/portfolio-web/portfolios/1'`

* Call endpoint to update or create a new portfolio:<br/>
`curl --location --request PUT 'http://localhost:8082/portfolio-web/portfolios/3001' \
--header 'Content-Type: application/json' \
--data-raw '{
	"description": "New Soccer Player 3",
	"imageUrl": "https://pbs.twimg.com/profile_images/1117967801652617216/i8PWXebo_400x400.jpg",
	"twitterUserName": "PibeValderramap",
	"title": "NEW USER 3"
}'`

* Integration Test:<br/>
Run `PortfolioApiIntegrationTest.java` located in *com.zemoga.project.integration* package.
