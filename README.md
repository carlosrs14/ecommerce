# Microservices Event-Driven Design with Saga Pattern

This project is an implementation of a reactive microservices architecture using **Spring Boot 3**, **Spring WebFlux**, and the **Saga** pattern for distributed transaction management. The system simulates an e-commerce flow (Orders, Inventory, and Payments) coordinated through events with **RabbitMQ**.

## Architecture

The system follows an Event-Driven Design and utilizes the **Orchestrated/Choreographed Saga** pattern to maintain eventual consistency between services.

### Main Components:
- **API Gateway**: Centralized entry point on port 8080.
- **Eureka Server**: Service discovery.
- **Order Service**: Orchestrator of purchase orders.
- **Product Service**: Catalog and inventory management.
- **Payment Service**: Payment processing.
- **RabbitMQ**: Messaging broker for asynchronous communication.
- **PostgreSQL**: Independent database for each microservice (Database-per-Service).

## Technologies Used

- **Java 21**
- **Spring Boot 3.5.x**
- **Spring Cloud 2025.x**
- **Spring WebFlux** (Reactive Programming)
- **Spring Data R2DBC** (Reactive access to SQL databases)
- **RabbitMQ** (Messaging)
- **Flyway** (Database migrations)
- **PostgreSQL 15**
- **Docker & Docker Compose**
- **Lombok & MapStruct**

## Prerequisites

- [Docker](https://www.docker.com/) and [Docker Compose](https://docs.docker.com/compose/) installed.
- Java 21 (optional, for local execution outside of Docker).
- Maven (optional).

## Setup and Execution

1. **Clone the repository:**
   ```bash
   git clone https://github.com/carlosrs14/microservices-event-driven-design.git
   cd microservices-event-driven-design
   ```

2. **Configure environment variables:**
   Copy the `example.env` file to `.env` and fill in the necessary values:
   ```bash
   cp example.env .env
   ```

3. **Start infrastructure with Docker Compose:**
   ```bash
   docker-compose up -d
   ```
   This will start:
   - Eureka Server (`http://localhost:8761`)
   - RabbitMQ Management (`http://localhost:15672`)
   - PostgreSQL (3 instances for each service)
   - Microservices (Order, Product, Payment)

## Main Endpoints (via API Gateway :8080)

All external traffic goes through the API Gateway, which routes to the underlying services.

### Order Service (`/api/v1/orders`)
- `GET /api/v1/orders`: List all orders.
- `GET /api/v1/orders/{id}`: Get order details.
- `POST /api/v1/orders`: Create a new order (Starts the Saga flow).

### Product Service (`/api/v1/products`)
- `GET /api/v1/products`: Product catalog.

## Saga Flow (Events)

1. **Order Service** receives a purchase request and creates the order in `CREATED` status.
2. A `ReserveInventoryCommand` is issued.
3. **Product Service** reserves stock:
   - If successful: Issues `InventoryReservedEvent`.
   - If it fails: Issues `InventoryRejectedEvent`.
4. **Payment Service** reacts to `InventoryReservedEvent` and processes the payment:
   - If successful: Issues `PaymentCompletedEvent`.
   - If it fails: Issues `PaymentFailedEvent`.
5. **Order Service** updates the final status of the order to `COMPLETED` or `CANCELLED`/`REJECTED` based on the received events.

---
Project developed as an example of implementing reactive microservices and distributed consistency patterns.
