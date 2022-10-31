--Создание таблицы Объявлений
CREATE TABLE ads
(
    id     integer PRIMARY KEY,
    author integer NOT NULL,
    image  varchar(32),
    price  integer NOT NULL,
    title  varchar(32)
);

--Создание таблицы Объявление комментарии
CREATE TABLE adsComment
(
    id     integer PRIMARY KEY,
    author integer NOT NULL,
    createdAt  varchar(32),
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



