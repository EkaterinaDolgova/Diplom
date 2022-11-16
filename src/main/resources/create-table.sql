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
    role       varchar(32)
);
--Создание таблицы Картинки
CREATE TABLE image
(
    id     bigint PRIMARY KEY,
    filePath  varchar(32),
    fileSize  bigint,
    mediaType   varchar(32),
    data      oid
);



