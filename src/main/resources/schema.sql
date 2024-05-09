--CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE IF NOT EXISTS Employee (
  id uuid default uuid_generate_v4(),
  username TEXT NOT NULL,
  password TEXT NOT NULL,
  role TEXT NOT NULL,
  isActive boolean NOT NULL
);
--DROP TABLE Employee;

--INSERT INTO Employee (username, password, role, isActive)
--VALUES ('test', 'password123', 'admin', true);

--INSERT INTO Employee(username,password,role,isActive) VALUES ('TEST','TEST','ADMIN',TRUE);