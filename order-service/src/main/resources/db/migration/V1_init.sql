CREATE TABLE orders (
    id UUID PRIMARY KEY gen_random_uuid(),
    product_id UUID NOT NULL,
    quantity INT NOT NULL,
    total_amount NUMERIC(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL CHECK(status IN ('CREATED', 'PENDING_PAYMENT', 'COMPLETED', 'CANCELLED', 'REJECTED')),
    created_at TIMESTAMP NOT NULL
);