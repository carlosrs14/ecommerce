CREATE TABLE IF NOT EXISTS  products (
    id UUID PRIMARY KEY gen_random_uuid(),
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    stock INT NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    active BOOLEAN NOT NULL
);
