CREATE TABLE CLIENTS_DATA.CLIENT
(
    client_id          SERIAL PRIMARY KEY,
    client_name        VARCHAR(250) NOT NULL,
    personal_name      VARCHAR(250) NOT NULL,
    address_id         bigint
);
