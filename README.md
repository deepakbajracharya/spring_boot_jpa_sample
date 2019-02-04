### Sample Application to demo spring-boot,jpa and rest using H2 database.

* Built using Java (requires Java 8).
* Uses __maven__ to build.
* Can be tested using __curl__.

# Building the application
Make sure that __mvn__ and __java__ is accessible from the command line.

* Clean and compile
```
mvn clean compile
```

* Run tests
```
mvn clean compile test
```

# Run application using maven and testing using curl
* Run the application using maven first
```
mvn compile spring-boot:run
``` 
The above command runs the application locally at 8080 port.

* Post message
To post message for a sender, POST method is used. The format of the url is
```
http://localhost:8080/user/<<username>>/send-message
```
Format of the message in JSON is 
```
{ "message" : "<< message text>>",
  "receivers" : [ "<<receiver's username>>", ...]
}
```

Multiple receivers are allowed. Hence, the value for __receivers__ is specified as an array.

```
curl --header "Content-Type: application/json" --request POST --data '{"message": "this is message", "receivers" : ["Receiver_1", "Receiver_2"]}' http://localhost:8080/user/Sender_1/send-message
```

Response from the server:
```
{"id":"bb6c8e5a-3a11-4095-8318-7048ba41d893","message":"this is message","sender":{"id":"a4aa854d-6aa2-4def-aa69-cdd786857826","username":"Sender_1","fullname":"Sender-One"},"dateCreated":"2019-02-04T23:32:34.944+0000","isPaliandrome":false}
```
Sender_1, Receiver_1, Receiver_2 are the users created during the start of the application.

* List messages sent by the user Sender_1
GET method is used for listing all the messages sent. The format of the URL is
```
http://localhost:8080/user/<< sender's username (must be urlencoded if special charter and space are included) >>/sent-messages
```

```
curl --header "Content-Type: application/json" --request GET http://localhost:8080/user/Sender_1/sent-messages
```
Response from the server:
```
[{"id":"bb6c8e5a-3a11-4095-8318-7048ba41d893","message":"this is message","sender":{"id":"a4aa854d-6aa2-4def-aa69-cdd786857826","username":"Sender_1","fullname":"Sender-One"},"dateCreated":"2019-02-04T23:32:34.944+0000","isPaliandrome":false}]
```
In the above case, the first id of "bb6c8e5a-3a11-4095-8318-7048ba41d893" is the ID for MessageSent.

Deleting the message for the message:
To delete, DELETE method is used. The format of the url is 
```
http://localhost:8080/user/<< sender's username >>/sent-messages/<< id for MessageSent >>
```


curl --header "Content-Type: application/json" --request DELETE http://localhost:8080/user/Sender_1/sent-messages/ad22e524-c9a2-4649-b517-6a135f1438c5

Response from the server:
```
Deleted: ad22e524-c9a2-4649-b517-6a135f1438c5
```