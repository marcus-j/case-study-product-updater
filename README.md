# Product Updater Case Study

### Task:

Please implement a service that reads the provided xml files and exposes the information via a RESTful API.

The API should provide two endpoints:
1. List all products with id, name and description
2. Show one product with all available information including current stock

Provided XML Files:
1. products.xml contains all available products
2. update-01.xml contains current stock for all products
3. update-02.xml contains an update for the product description

Out of scope:
- The service does not need to persist the data
- API endpoint does not need to be protected

### Solution

1. build
2. run `java -jar build\libs\interview-backend-0.1.0.jar`
3. open http://localhost:8080/products
4. open http://localhost:8080/products/43b105a0-b5da-401b-a91d-32237ae418e5