# Stock Listing App BE

This project was built with Kotlin, Spring and Coroutine. The database used in this project is PostgreSQL.

## Instruction to Run in Local Environment

1. Install IDE for kotlin, exp: INTELLIJ IDEA
2. Install JDK17: https://www.oracle.com/sg/java/technologies/downloads/
3. Install PostgreSQL:https://www.postgresql.org/download/
4. Configure database credential at path: file-upload-be\src\main\resources\application.properties
5. Create a file "schema.sql" at path: file-upload-be\src\main\resources\schema.sql
  
  Paste the following command into "schema.sql"
  
  create table if not exists stock (
    id serial primary key,
    invoiceno varchar(255),
    stockcode varchar(255),
    description varchar(255),
    quantity int,
    invoiceDate varchar(255),
    unitprice REAL,
    customerid varchar(255),
    country varchar(255)
);

7. Create a database "stocksdb" in PostgreSQL.
8. Open the project folder and run it with INTELLIJ IDEA.


