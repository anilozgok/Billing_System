# Billing_System

Goal of the project is to create an billing system for purchase specialists.
Specialists can add new bills to the system within their allowed limit and perform some read operations.

Techs: MVC architecture, Java, Spring Boot, PostgreSQL.


### Requirements
* Docker: for installation
* Java 17+: for development
* Maven: for development
* Postman: for testing


### Installation

You can directly run the project using docker-compose

```shell
docker compose up
```



### Using project

To Save Bill:
```http request
http://localhost:8080/api/bill/save

Sample Request Body:

{
    "bill": {
        "billNo": "TR5500",
        "firstName": "Anıl Can",
        "lastName": "Özgök",
        "email": "anil@mail.com",
        "amount": 1500,
        "productName": "product1"
    }
}
```

To Get All Bills:
```http request
http://localhost:8080/api/bill/get/TR5500
```

To Get Accepted Bills:
```http request
http://localhost:8080/api/bill/get-accepted
```

To Get Rejected Bills:
```http request
http://localhost:8080/api/bill/get-rejected
```

Also, you can find HTTP requests on postman collection file.

