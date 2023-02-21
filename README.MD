## Kameleoon Trial Task
### Description
https://developers.kameleoon.com/back-end-trial-task.html  

You should implement a REST API for the web interface in the two wireframes below.

![wireframe 1](https://developers.kameleoon.com/images/quote-list.png)
![wireframe 2](https://developers.kameleoon.com/images/quote-detail.png)
Project requirements:  
- source code should be published on https://github.com/;
- Docker image should be published on https://hub.docker.com/;
- project should have docker-compose.yaml for local deploy, OR you can deploy it on a public hosting provider (Heroku, etc);
- use any in-memory database (Derby, H2, SQLite);
- use Java and Spring Boot.  

The API should allow:
- creation of a user account (deletion and other CRUD operations not requested). User properties are name, email, 
password and date of creation;
- addition, viewing (including a method to get a random quote), modification, and deletion of quotes (real deletion 
from the database, not just via an archive flag). Quote properties include content, date of creation / update, link to 
user who posted it, link to votes;
- voting on quotes (either upvote or downvote);
- view of the top and worse 10 quotes, the details of each quote, and ideally a graph of the evolution of the votes over time.

Not requested: frontend part, authentication mechanism, and account rights management.

If you have some questions about business logic not covered here, you can choose yourself the answer, no need to ask us. 
At this point, the goal is to evaluate your coding skills, not how you respect a technical specification (this is also 
important, but can come later :-).

The full exercise can take about 6-8 hours (for a middle-level developer; longer for a junior) but the goal is not 
necessarily to do everything. You need to show your development skills, respect of best practices, understanding of the 
web development model (client, server), ability to deliver your work to a production-like environment, and your productivity.  
### Requests examples
Create user account:
```
POST /api/users

{
    "name":"alice",
    "email":"alice@mail.com",
    "password":"pass"
}
```
Add a quote:
```
POST /api/quotes?userId={ID_OF_POSTER}

{
    "content":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod..."
}
```
Get all quotes list:
```
GET /api/quotes
```
Get all quotes of particular user:
```
GET /api/quotes?userId={ID_OF_POSTER}
```
Get a random quote:
```
GET /api/quotes?random
```
Edit quote:
```
PATCH /api/quotes/{QUOTE_ID}

{
    "content":"Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris..."
}
```
Delete a quote:
```
DELETE /api/quotes/{QUOTE_ID}
```
Upvote or downvote:
```
POST /api/quotes/{QUOTE_ID}?userId={VOTER_ID}&vote={TRUE_IF_UPVOTE_OTHERWISE_FALSE}
```
View of the top and worse 10 quotes:
```
GET /api/quotes?best

GET /api/quotes?worst
```
Full OpenAPI documentation available at:
```
http://localhost:8080/swagger-ui.html
```
### Build and usage
Build project using Maven:
```
mvn clean package
```
Launch on your local machine:
```
java -jar target/kameleoon-trial-task-0.0.1-SNAPSHOT.jar
```
### Docker
For launching this app inside Docker container go to project root folder and enter in your terminal:
```
docker compose up
```
OR get an image from [DockerHub](https://hub.docker.com/r/lordgarrish/kameleoon-trial-task-quote-api):  
```
docker pull lordgarrish/kameleoon-trial-task-quote-api
```
Run image:
```
docker run -p 8080:8080 --name quote-api lordgarrish/kameleoon-trial-task-quote-api
```
