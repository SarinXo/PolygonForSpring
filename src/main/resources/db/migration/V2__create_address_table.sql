CREATE TABLE CLIENTS_DATA.ADDRESS
(
    id          SERIAL PRIMARY KEY,
    city        VARCHAR(250) NOT NULL,
    street      VARCHAR(250) NOT NULL,
    house       INT NOT NULL,
    floor       INT NOT NULL,
    flat_number INT NOT NULL
);
