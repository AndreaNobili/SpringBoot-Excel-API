# Spring Boot Excel API - Apache POI

This project contains code examples for working with Excel files (Apache POI library) and how to store the content into a DB table
using Spring Data JPA.


## Running the project

1. Open terminal and navigate to your project
2. Type command **mvn install**
3. Type command **mvn spring-boot:run**

## Packaging application

**mvn clean package**  

## Running tests
**mvn test**

## API use cases:

1. Send an Excel Document, parse it and persist the content on a DB table:
curl -F file=@"	YOUR_PROJECT_FOLDER/src/main/resources/documents/sample.xlsx" http://localhost:8080/api/upload_customers_orders

2. Obtain the data stored into the DB table:
curl http://localhost:8080/api/upload_customers_orders http://localhost:8080/api/customers/orders

## Author

Andrea Nobili
Software Engineer  
<br>
**E-mail**: nobili.andrea@gmail.com  
**Skype**:  nobili.andrea@gmail.com   
**LinkedIn**: https://www.linkedin.com/in/anobili/

