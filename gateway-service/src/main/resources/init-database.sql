-- Create schema if it does not exist
CREATE SCHEMA IF NOT EXISTS media_hub;

-- Create the users table
CREATE TABLE media_hub.users
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    role       VARCHAR(50)  NOT NULL
);