CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    names     TEXT NOT NULL,
    email     TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL
);