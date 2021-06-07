CREATE SCHEMA remote_technical_test;

--All the data from test_accounts is inserted into here.
--Single apostrophes are escaped with another single apostrophe for last names which needed them.
CREATE TABLE remote_technical_test.test_accounts
(
    account_id VARCHAR PRIMARY KEY NOT NULL,
    first_name VARCHAR NOT NULL,
    surname VARCHAR NOT NULL,
    email VARCHAR NOT NULL
);


--Valid Accounts are saved to this table.
CREATE TABLE remote_technical_test.valid_accounts
(
    meter_reading VARCHAR NOT NULL,
    account_id VARCHAR PRIMARY KEY NOT NULL,
    reading VARCHAR NOT NULL
);
