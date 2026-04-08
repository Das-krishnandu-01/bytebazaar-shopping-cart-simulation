CREATE DATABASE IF NOT EXISTS shopping_cart_db;
USE shopping_cart_db;

-- Products Table
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;

CREATE TABLE products (
    product_id INT PRIMARY KEY,
    name VARCHAR(100),
    price DOUBLE,
    stock INT
);

INSERT INTO products VALUES
(1, 'Laptop', 55000, 10),
(2, 'Headphones', 2000, 20),
(3, 'Mouse', 500, 30);

-- Orders Table (CREATE FIRST)
CREATE TABLE orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    total_amount DOUBLE,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Order Items Table (CREATE AFTER orders)
CREATE TABLE order_items (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT,
    product_id INT,
    quantity INT,
    price DOUBLE,
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);