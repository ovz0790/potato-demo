# potato-demo

A little demo application that can manage potato bags on the biggest potato
market. In the potato business, you have different potato suppliers and the potato bags
you sell are also different in some characteristics.

It provides RESTful APIs for the following operations:
1. Get a list of potato bags for sale in the market.
2. Add a new potato bag to the market.

Getting Started

- Download project
- Configure if you need
- Buid it with maven

Configuration

To default counfugured application port is 8085, use application.yml
parameter for change it:
port: ${PORT_TO_EXPOSE:8085}

Default count bags for getting request could be configured with application.yml
 parameter:

 bags:
   page:
     size: 3

Storing data

All data storing in H2 database. Tables for store creating with liquibase script
You can change database provider by configuring spring  jpa parameters

Running application

- Run as a java app: java -jar potato-bags.jar
    or running PotatoApplication::main from IDE
- Use REST API for calling HTTP Methods

Input and output data

Bag data contain fields:
• ID
• Number of potatoes in a bag (minimum 1, maximum 100)
• Supplier of a bag (must be one of "De Coster", "Owel", "Patatas Ruben",
"Yunnan Spices")
• Date and time when a bag was packed
• Price (number from 1 to 50)


REST API description

1. For get list of potato bags send GET request for url:

GET:
- for geting 10 bags: http://localhost:8085/ui/nl/potato/market/bags?count=10
- for geting 3 bags(default value): http://localhost:8085/ui/nl/potato/market/bags

Example output json data:
[
  {
    "id": "60d0fa3c-84c3-4e5c-8f4d-4b28e1b6bf84",
    "potatoesNumber": 10,
    "supplier": "Patatas Ruben",
    "packedDate": "2018-03-27T08:53:27.956+0000",
    "price": 11
  },
  {
    "id": "5bf0a22d-fbe2-43c7-97ae-19c029b5c334",
    "potatoesNumber": 10,
    "supplier": "Patatas Ruben",
    "packedDate": "2018-03-27T08:53:27.956+0000",
    "price": 12
  }
  ...
]
2. For add a new potato bag to the market send POST request for url:
POST:
http://localhost:8085/ui/nl/potato/market/bag

Use HTTP header "Content-Type"="application/json"

Example input json data in a request body:
{
    "potatoesNumber": 10,
    "supplier": "Patatas Ruben",
    "packedDate": "2018-03-27T08:53:27.956+0000",
    "price": 12
  }

You could also see examples of data inputs in test data json file
in a ~\src\test\resources\test-data\input-bags.json

This project is licensed under the GNU GENERAL PUBLIC LICENSE - see the LICENSE.md file for details