--Создание таблицы Объявлений
CREATE TABLE advert
(
    id     bigint        PRIMARY KEY,
    users bigint NOT NULL,
    image  varchar(32),
    price  integer NOT NULL,
    title  varchar(32)
);

--Создание таблицы Объявление комментарии
CREATE TABLE comment
(
    id     bigint PRIMARY KEY,
    users integer NOT NULL,
    createdate  varchar(32),
    text  varchar(32)
);

--Создание таблицы Пользователи
CREATE TABLE users
(
    id     bigint PRIMARY KEY,
    email      varchar(32),
    firstName  varchar(32),
    lastName   varchar(32),
    phone      varchar(32),
    role       varchar(32),
    username varchar(50) not null,
    password varchar(500) not null,
    enabled boolean not null
);
--Создание таблицы Картинки
CREATE TABLE image
(
    id     bigint PRIMARY KEY,
    filePath  varchar(32),
    fileSize  bigint,
    mediaType   varchar(32),
    "data"      oid
);

create table users(
                      username varchar(50) not null primary key,
                      password varchar(500) not null,
                      enabled boolean not null
);

create table authorities (
                             username varchar(50) not null,
                             authority varchar(50) not null
);
create unique index ix_auth_username on authorities (username,authority);



