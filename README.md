# Code Kata challenge
Simple REST-ful service for vote counting and results generating system.

### Technologies used
 - Java 8
 - Maven
 - Jersey
 - Jetty

### Requirement

 - JDK 1.8
 - Maven 3.2

### Example
 
```
@Path("poll-service")
public class PollService {

	...

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPoll() {
		...
	}

	@POST
	@Path("/{pollName}")
	@Produces(MediaType.APPLICATION_JSON)
    	@Consumes(MediaType.APPLICATION_JSON)
	public Response countMeUp(
		@PathParam("pollName") String pollName, Vote vote) {
		...
	}
```

### Build & Rest Server

```
mvn package
mvn exec:java
```
Rest Server start at After that you can use curl or your browser to test the different services.

### Example
Request
```
curl -i http://localhost:2222/poll-service/ -H'Accept:application/json'
```
Response
```
HTTP/1.1 200 OK
Date: Thu, 11 May 2017 21:05:00 GMT
Content-Type: application/json
Content-Length: 53
Server: Jetty(9.2.14.v20151106)

{"totalVotes":15,"voteMap":{"A":5,"B":1,"C":2,"D":7}}
```

Request
```
curl -i -H "Content-Type: application/json" -X POST -d '{ "voter" : "AG", "candidate" : "D"}' http://localhost:2222/poll-service/poll1  -H'Accept:application/json'
```
Response
```
HTTP/1.1 201 Created
Date: Thu, 11 May 2017 21:04:05 GMT
Content-Type: application/json
Content-Length: 12
Server: Jetty(9.2.14.v20151106)

vote aquired
```



License
----

MIT
