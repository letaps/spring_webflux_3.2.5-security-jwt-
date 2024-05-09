--CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

--DROP TABLE Employee;

--USE test_db;


CREATE TABLE IF NOT EXISTS Employee (
  id uuid default uuid_generate_v4(),
  username TEXT NOT NULL,
  password TEXT NOT NULL,
  role TEXT NOT NULL,
  isActive boolean NOT NULL
);
