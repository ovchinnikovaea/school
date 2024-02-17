CREATE TABLE persons (
id SERIAL PRIMARY KEY,
name TEXT,
age INTEGER,
driver_license BOOLEAN,
car_id INTEGER REFERENCES cars(id)
);

CREATE TABLE cars (
id SERIAL PRIMARY KEY,
car_make TEXT,
model TEXT,
cost INTEGER
);