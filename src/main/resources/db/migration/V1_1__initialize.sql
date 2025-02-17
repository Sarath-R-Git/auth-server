CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(120) NOT NULL,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    email VARCHAR(255),
    role VARCHAR(50) NOT NULL,
    UNIQUE KEY `uk_username` (`username`),
    UNIQUE KEY `uk_email_id` (`email`)
);
