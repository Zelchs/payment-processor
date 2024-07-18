# Payment Processor

Payment Processor is a Spring Boot application for processing payments. It provides RESTful API endpoints for creating,
retrieving, and uploading payments.

## Features

- Create a new payment
- Upload payments from CSV file
- Retrieve all payments

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- H2 Database
- Jakarta Bean Validation
- OpenCSV for CSV parsing
- Mockito Testing

## Getting Started

To run the Payment Processor application locally, follow these steps:

1. Clone the repository:

   ```shell
   git clone https://github.com/Zelchs/payment-processor.git
   ```
2. Navigate to the project directory:

   ```shell
   cd payment-processor
   ```

3. Build the application:

   ```shell
   ./gradlew clean build
   ```

4. Run the application:

   ```shell
   ./gradlew bootRun
   ```

## Usage

### Creating a Payment

To create a new payment, send a POST request to the /payments endpoint with the following JSON payload:

```json
{
  "amount": 15.0,
  "debtorIban": "LT356437978869712537"
}
```

### Uploading Payments from CSV

To upload payments from a CSV file, send a POST request to the /payment-files endpoint with a multipart form-data
request containing the CSV file.

### Retrieving payments

To retrieve all payments or filter payments by debtor IBAN, send a GET request to the /payments endpoint. Optionally,
you can include a query parameter debtorIban to filter payments by debtor IBAN:

```shell
GET /payments
GET /payments?debtorIban=LT356437978869712537
```
