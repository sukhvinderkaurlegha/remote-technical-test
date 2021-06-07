# Remote Technical Test By Sukhvinder Legha

This is a Java 11, Spring Boot 2 Application with REST API calls. 

The database used is PostgreSQL.

The schema and data for the database tables are saved in the resources folder.
By using excel, I generated insert statements for all the data for test_accounts.

In this application, we have the following API calls: 
- POST Mapping: updateValidAccountsTable (http://localhost:8080/v1/api/meter-reading-uploads)
    This posts the valid accounts data to a new table called valid_accounts.
    The logging will also show the number of valid accounts, the number of accounts before the validation and the number of valid accounts including duplicates.

- GET Mapping: getAllAccounts, getAllAccountIds, getAllValidAccountsWithoutDuplicates
    These were mainly used to access data, but I've listed the urls for them below anyway.
    http://localhost:8080/v1/api/getAllAccounts
    http://localhost:8080/v1/api/getAllAccountIds
    http://localhost:8080/v1/api/getValidAccountsCountWithoutDuplicates
    
    
## Database connectivity
Set your local database up using the credentials in the application.properties file.
I used docker on my system.

## Testing Data
I used Postman to access the endpoints.

Thank you


