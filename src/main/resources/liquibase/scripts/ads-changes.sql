-- liquibase formatted sql

-- changeset kew:1
CREATE TABLE IF NOT EXISTS advert --Создание таблицы Объявлений
(
    id     bigint        PRIMARY KEY,
    users bigint NOT NULL,
    image  varchar(32),
    price  integer NOT NULL,
    title  varchar(32)
    );

CREATE TABLE IF NOT EXISTS comment --Создание таблицы Объявление комментарии
(
    id     bigint PRIMARY KEY,
    users integer NOT NULL,
    createdate  varchar(32),
    text  varchar(32)
    );

CREATE TABLE IF NOT EXISTS users -- Создание таблицы Пользователи
(
    id     bigserial PRIMARY KEY,
    email      varchar(32),
    firstName  varchar(32),
    lastName   varchar(32),
    phone      varchar(32),
    username varchar(50) not null unique ,
    password varchar(500) not null,
    enabled boolean not null

    );

CREATE TABLE IF NOT EXISTS image -- --Создание таблицы Картинки
(
    id     bigint PRIMARY KEY,
    filePath  varchar(32),
    fileSize  bigint,
    mediaType   varchar(32),
    "data"      oid
    );

CREATE TABLE IF NOT EXISTS authorities (
    username varchar(50) not null,
    authority varchar(50) not null,
    FOREIGN KEY (username)
    REFERENCES users (username)
    );

create unique index IF NOT EXISTS ix_auth_username on authorities (username,authority);

