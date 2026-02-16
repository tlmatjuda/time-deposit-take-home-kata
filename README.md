# Time Deposit Refactoring Kata - Take-Home Assignment

## XA Bank Time Deposit

### Context
A junior developer implemented domain logic for a time deposit system but did not complete the API functionality. Your task is to refactor the existing codebase to implement all required functionalities based on the provided business requirements, ensuring no breaking changes occur.

### Requirements

1. **API Endpoints**:
    - Create a RESTful API endpoint to update the balances of all time deposits in the database.
    - Create a RESTful API endpoint to retrieve all time deposits.
        - The GET endpoint should return a list of all time deposits with the following schema:
            - `id`
            - `planType`
            - `balance`
            - `days`
            - `withdrawals`

2. **Database Setup**:
    - Store all time deposit plans in a database.
    - Define the following tables:
        - `timeDeposits`:
            - `id`: Integer (primary key)
            - `planType`: String (required)
            - `days`: Integer (required)
            - `balance`: Decimal (required)
        - `withdrawals`:
            - `id`: Integer (primary key)
            - `timeDepositId`: Integer (foreign key, required)
            - `amount`: Decimal (required)
            - `date`: Date (required)

3. **Interest Calculation**:
    - Implement logic to calculate monthly interest based on the plan type:
        - **Basic Plan**: 1% interest
        - **Student Plan**: 3% interest (no interest after 1 year)
        - **Premium Plan**: 5% interest (interest starts after 45 days)
    - No interest is applied for the first 30 days for any existing plans.

4. **Refactoring Constraints**:
    - Do not introduce breaking changes to the shared `TimeDeposit` class or modify the `updateBalance` method signature.
    - Ensure the design is extensible to accommodate future complexities in interest calculations.

5. **Code Quality**:
    - Adhere to SOLID principles, design patterns, and clean code practices where applicable.

### Important Guidelines
- The existing `TimeDepositCalculator.updateBalance` method is functioning correctly. Ensure its behavior remains unchanged after refactoring.
- The final solution must include **exactly two API endpoints**. Do not develop additional endpoints.
- **Do not** create a pull request or a new branch in the ikigai-digital repository. Instead, fork the repository into your own GitHub repository and develop the solution there.
- Handling invalid input or exceptions is not required.
- Use any tools, frameworks, or libraries you find suitable.
- In case of ambiguity, make logical assumptions and justify them in code comments.

### Preferred Stack
- Use an OpenAPI Swagger contract.
- Embrace Hexagonal Architecture.
- Follow atomic commit practices.
- Utilize testcontainers.

### Submission Instructions
- Provide clear instructions on how to trigger the endpoints using the Swagger contract.
- Email the link to your public GitHub repository.

---

## Solution Guide

### Run Instructions

#### Prerequisites
- Java 17
- Maven 3.9+
- Docker-compatible runtime (Docker Desktop, OrbStack, or Rancher)

#### Start the API
```bash
cd java
mvn spring-boot:run
```

Notes:
- The app runs on port `8089` by default.
- Spring Boot uses `java/docker-compose.yml` to manage PostgreSQL for local runs.
- Flyway migrations run automatically on startup:
  - `V1__init_schema.sql`
  - `V2__seed_demo_data.sql`

#### Run Tests
```bash
cd java
mvn test
```

### Swagger / OpenAPI
- Swagger UI: `http://localhost:8089/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8089/v3/api-docs`

### API Endpoints (Exactly Two)
1. `GET /time-deposits`
2. `PATCH /time-deposits/balances`

### How to Call the Endpoints

#### GET all time deposits
```bash
curl --request GET "http://localhost:8089/time-deposits" \
  --header "Accept: application/json"
```

Example response:
```json
[
  {
    "id": 1,
    "planType": "basic",
    "balance": 1000.0,
    "days": 90,
    "withdrawals": []
  },
  {
    "id": 2,
    "planType": "student",
    "balance": 2000.0,
    "days": 200,
    "withdrawals": []
  },
  {
    "id": 3,
    "planType": "premium",
    "balance": 3000.0,
    "days": 60,
    "withdrawals": []
  }
]
```

#### PATCH update all balances
```bash
curl --request PATCH "http://localhost:8089/time-deposits/balances" \
  --header "Accept: application/json"
```

Example response:
```json
[
  {
    "id": 1,
    "planType": "basic",
    "balance": 1000.83,
    "days": 90,
    "withdrawals": []
  },
  {
    "id": 2,
    "planType": "student",
    "balance": 2005.0,
    "days": 200,
    "withdrawals": []
  },
  {
    "id": 3,
    "planType": "premium",
    "balance": 3012.5,
    "days": 60,
    "withdrawals": []
  }
]
```

### Assumptions Made
- Unknown `planType` values receive no interest (no error is thrown).
- `PATCH /time-deposits/balances` is an action endpoint that recalculates all records from DB state and requires no request body.
- PATCH returns updated records with `200 OK` instead of `204 No Content`.
- Existing calculator behavior is preserved exactly, including rounding behavior from `new BigDecimal(double)`.
- Interest thresholds are interpreted per current working behavior:
  - Global interest applies only when `days > 30`
  - Student interest stops when `days >= 366`
  - Premium interest starts only when `days > 45`
- `withdrawals` is included in response shape and currently returns as an empty list in API mapping.
- Seed data is included via Flyway for deterministic local testing and demoing.
