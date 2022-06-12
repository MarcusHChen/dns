## How Does it Work

This service provides one endpoint to get the ipv4 value of a url

```text
/ipv4/{encodedUrl}
```

## Usage
This service is connected to an H2 database and its table DOMAINS. Data in the file 'data.sql' is inserted into the database when the service first runs. When the service is running the H2 database console is available at http://localhost:8080/h2-console

Login with username 'sa' and password 'password'

Here are examples for the endpoint:

```text
curl http://localhost:8080/ipv4/www%2Eexample%2ECOM
curl http://localhost:8080/ipv4/
curl http://localhost:8080/ipv4/com

```

