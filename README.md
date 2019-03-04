This Demo project is created using Spring Boot and REST API
For Graph parsing, DFS algorithm is used. 

Usage instructions
Note: List of city routes available in -/src/main/resources/cityNetwork.txt

1. In the browser provide the below URL to get if 2 cities are connected 
http://localhost:8080/api/connected/Newark/Boston
2. To check the health of application
http://localhost:8080/api/connected/ping
3. REST API documentation provided using swagger. Browser through the API using below URL
http://localhost:8080/swagger-ui.html