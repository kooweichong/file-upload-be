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