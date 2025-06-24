CREATE TABLE users
(
    id         BIGSERIAL NOT NULL,
    username   VARCHAR(20) UNIQUE NOT NULL,
    email      VARCHAR(50) UNIQUE NOT NULL,
    password   VARCHAR(75) NOT NULL,
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    CONSTRAINT pk_users PRIMARY KEY (id)
);