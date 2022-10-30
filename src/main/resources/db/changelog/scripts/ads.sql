--Создание таблицы ads "Объявления"
-- liquibase formatted sql

CREATE TABLE ads
(
    id        integer       PRIMARY KEY,
    author    integer       NOT NULL,
    image     varchar(32),
    price     integer,
    title     varchar(32)
);