# REST API - Beer Catalogue


The implemented REST API allows different manufacturers to manage their beer catalogue. Beer consumers can look up the beer catalogue. 

# Implemented functionalities

1. Create the data model and the CRUD endpoints for a beers API, HTTP RESTFUL compliant, without the need to persist any information yet. 
2. Add the required persistence/service layer to the defined API.
3. Let's make this secure.
4. Add pagination to the collection endpoints.
5. Include the beer search functionality to the API: users should be able to search by any beer attribute, such as name, type, nationality, manufacturer...



# Technical decisions


1. Use **H2 database running** as inmemory database.

2. Use of **Jib maven plugin** in order to generate the Docker image.

3. Use of **Swagger** for the API documentation.



# How to run the project


The project can be deployable to a Docker container following the below steps:

Generate the image docker taking into account the JIB definition in the pom.xml project running the below command:

```
cd /beer-catalogue
mvn compile jib:dockerBuild
```

Consult the created images. It must be displayed the beer-catalogue image (latest by default):

```
docker images
```

Run the container in the port 8080:

```
docker run -p 8080:8080 beer-catalogue
```

# Useful Endpoints


**Swagger**

The Swagger API documentation can be accessed using the url [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)
Here you can find and test the endpoints and model definitions.

**H2 console**

The H2 console can be accessed using the url [http://localhost:8080/h2-console/login.jsp](http://localhost:8080/h2-console/login.jsp)
Find below the data access:

  * Drive Class: org.h2.Driver
  * User Name: sa
  * Password: Not required

Here you can consult the Database tables and data automatically inserted on application starting.


# Users


Admin user with role ADMIN. Credentials:

  * User Name: admin
  * Password: 1234
  
Manufacturer user with role MANUFACTURER associated to the manufacturer Damm (id=2). Credentials:

  * User Name: dammalias
  * Password: 1234
 
Manufacturer user with role MANUFACTURER associated to the manufacturer Heineken (id=4). Credentials:

  * User Name: heinekenalias
  * Password: 1234
  
Manufacturer user with role MANUFACTURER associated to the manufacturer Mahou (id=5). Credentials:

  * User Name: mahoualias
  * Password: 1234

