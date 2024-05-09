CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS Employee (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Employee (
    id uuid default uuid_generate_v4(),
    username TEXT NOT NULL,
    password integer NOT NULL,
    role TEXT NOT NULL,
    isActive boolean NOT NULL,
);