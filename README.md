Download the code from the repository https://github.com/bhaskargandu/Country-app.git

Repo have two projects country-frontend is the UI and microservice-countries is the backend 

make sure that there is no application running on ports 8080 and 4200 using the below commands

	netstat -aof | findstr :8080
	netstat -aof | findstr :4200

Steps to run the backend application


# Country-app
Application : Country App MicroServices
port:8080

Pre-requisite: Java 11 , Source code checkout tools,STS and java and maven path are set correctly

1) import the project in STS IDE or eclipse as existing maven project and make sure the project is pointed to JAVA11 in the classpath
2) run spring boot application
3) this should launch the backend application on the port 8080
4) you can test the positive and negative scenarios using the below URL from the postman or browser
	GET http://localhost:8080/countries
	GET http://localhost:8080/countries/{name} //where name is full name of the country ex. Finland
	example:
	http://localhost:8080/countries/ees
	{
    "timestamp": "2021-03-09T21:24:16.976+00:00",
    "message": "country not found for the requested country",
    "details": "uri=/countries/ees"
	}
	http://localhost:8080/countries/Finland
	  Response:
	{
    "name": "Finland",
    "country_code": "FI",
    "capital": "Helsinki",
    "population": 5491817,
    "flag_file_url": "https://restcountries.eu/data/fin.svg"
	}
	we can disconnect from internet and hit the URL  http://localhost:8080/countries/Finland
	{
    "timestamp": "2021-03-09T21:25:20.971+00:00",
    "message": "Error fetching data from external countries service",
    "details": "uri=/countries/ees"
	}
	
Application : Country App Front end
port:4200


Launching steps:

1) Pre-requisite: Node Js v12.14.1, Source code checkout tools and angular cli

Node js can be downloaded from the below link: Chose the OS and bit
https://nodejs.org/en/download/


2) Make sure the back end application is running on the port 8080

Steps to launch the application
===================================

1) country-frontend using GIT command or IDE -
	  you can follow the below steps to install eclipse IDE or we can use other IDES
	  https://www.eclipse.org/community/eclipse_newsletter/2017/february/article1.php

2) Use the npm install to intall all dependencies from node repository

3) ng serve is the command to launch the application

4) Application can be accessed using below URL's
	http://localhost:4200

5) Search Country name with full name for example Finland