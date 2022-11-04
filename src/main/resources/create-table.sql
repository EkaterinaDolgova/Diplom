--Создание таблицы Объявлений
CREATE TABLE advert
(
    id     bigint        PRIMARY KEY,
    users integer NOT NULL,
    image  varchar(32),
    price  integer NOT NULL,
    title  varchar(32)
);

--Создание таблицы Объявление комментарии
CREATE TABLE adscomment
(
    id     bigint PRIMARY KEY,
    users integer NOT NULL,
    createdat  varchar(32),
    text  varchar(32)
);

--Создание таблицы Пользователи
CREATE TABLE users
(
    id     integer PRIMARY KEY,
    email  varchar(32),
    firstName  varchar(32),
    lastName   varchar(32),
    phone      varchar(32)
);



