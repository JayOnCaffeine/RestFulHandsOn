# Restful hands on

*   Rest is a web standard.
*   Rest stands for REpresentational State Transfer.
*   Rest uses HTTP protocol for data transfer.

REST uses different format like text, XML and JSON to represent resources.

In REST architecture each resource is identified by URIs (Uniform Resource Identifier).
REST server provides access to resources and REST client accesses and presents the resources.

## RESTFul Web Services

Web services is nothing but providing standards to access resources on the internet. Different programming languages running on different platform can communicate using this standard. This provides interoperability between different programming languages (Eg. Java, C, C#, Python) and different platform (Eg. Windows, Linux).

REST uses HTTP methods to share resources over internet. Following HTTP methods are commonly used in REST based architecture,

*   GET - Read access to resource.
*   PUT - Create new resource.
*   POST - Update existing or create new resource.
*   DELETE - Remove resource.
*   OPTIONS - Get supported operations on a resource.

This tutorial creates todo with following REST service:

HTTP Method | URI           | Operation                | Operation Type
------------|---------------|--------------------------|---------------
GET         | /ToDoService/todos   | Get list of ToDo         | Read
GET         | /ToDoService/todos/1 | Get first ToDo           | Read
PUT         | /ToDoService/todos/2 | Insert second ToDo       | Idempotent
GET         | /ToDoService/todos/2 | Get second ToDo          | Read
POST        | /ToDoService/todos/2 | Update second ToDo       | N/A
POST        | /ToDoService/todos/3 | Create third ToDo        | N/A
GET         | /ToDoService/todos/3 | Get third ToDo           | Read
DELETE      | /ToDoService/todos/2 | Delete second ToDo       | Idempotent
OPTIONS     | /ToDoService/todos   | List supported operation | Read
