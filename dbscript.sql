CREATE database bank_management;

CREATE customer(customer_id INT GENERATED ALWAYS AS IDENTITY,
customer_password VARCHAR(50),
customer_first_name VARCHAR(50),customer_last_name VARCHAR(50),
customer_contact VARCHAR(60),account_type VARCHAR(20),
account_balance double precision(50));
customer_address VARCHAR(60),account_id INT,PRIMARY KEY (customer_id));

CREATE employee(employee_id INT,employee_password VARCHAR(20),
employee_first_name VARCHAR(40),employee_last_name VARCHAR(40),
employee_address VARCHAR(40),employee_contact VARCHAR(50));

CREATE TABLE transaction_details(
   transaction_id INT GENERATED ALWAYS AS IDENTITY,
   from_account_id INT,
   to_account_id INT,
   transfer_amount double precision,
   transfer_date DATE NOT NULL DEFAULT CURRENT_DATE,
   PRIMARY KEY(transaction_id ),
   CONSTRAINT fk_customer
   FOREIGN KEY(to_account_id) 
   REFERENCES customer(customer_id),CONSTRAINT fk_customer2
   FOREIGN KEY(from_account_id) 
   REFERENCES customer(customer_id)
);


INSERT INTO employee(employee_password,employee_first_name,
employee_last_name,employee_address,employee_contact)
VALUES('123','Sam','Hilton','wilson drive','sam@gmail.com');

INSERT INTO customer(customer_password,customer_first_name,
customer_last_name,customer_contact,customer_address,account_id,account_type,account_balance) 
VALUES('456','Jack','David','Jack@gmail.com','lakewood road',9999,'checking',500.00) ;

INSERT INTO transaction_details(from_account_id,to_account_id,transfer_amount,transfer_date) 
values(8,9,500,'02/22/2022')
