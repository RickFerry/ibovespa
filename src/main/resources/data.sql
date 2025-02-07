CREATE TABLE Trade (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    trade_value DOUBLE,
    client_sector VARCHAR(255),
    next_payment_date DATE
);

INSERT INTO Trade (trade_value, client_sector, next_payment_date) VALUES 
(2000000, 'Private', '2025-12-29'),
(400000, 'Public', '2020-07-01'),
(5000000, 'Public', '2024-01-02'),
(3000000, 'Public', '2023-10-26'),
(1500000, 'Private', '2023-09-15'),
(1200000, 'Public', '2023-08-10'),
(800000, 'Private', '2023-12-20'),
(900000, 'Public', '2023-06-30'),
(3000000, 'Private', '2023-11-25'),
(2500000, 'Public', '2023-10-15');