CREATE TABLE IF NOT EXISTS payments (
    id UUID PRIMARY KEY gen_random_uuid(),
    order_id UUID NOT NULL,
    amount NUMERIC(10,2) NOT NULL,
)