CREATE TABLE products (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	quantity INTEGER NOT NULL CHECK (quantity >= 0),
	price NUMERIC(10, 2) NOT NULL CHECK (price >= 0),
	discount_percent NUMERIC(5, 2) DEFAULT 0 CHECK (discount_percent >= 0 AND discount_percent <= 100)
);

CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	username VARCHAR(50) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL,
	emai VARCHAR(100) NOT NULL UNIQUE,
	role VARCHAR(20) NOT NULL CHECK (role IN 'USER', 'ADMIN')
);

-- Таблица заказов
CREATE TABLE orders (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    total_price NUMERIC(12, 2) NOT NULL
);

-- Таблица элементов заказа
CREATE TABLE order_items (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
    product_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    discount_percent NUMERIC(5, 2) DEFAULT 0,
    total_price NUMERIC(12, 2) NOT NULL
);

CREATE TABLE refresh_token (
    id BIGSERIAL PRIMARY KEY,
    token VARCHAR(255) NOT NULL,
    expires_at TIMESTAMP,
    user_id BIGINT UNIQUE, -- UNIQUE: один refresh токен на одного пользователя
    CONSTRAINT fk_refresh_user FOREIGN KEY (user_id) REFERENCES users(id)
);