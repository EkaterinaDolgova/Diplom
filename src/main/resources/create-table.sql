--Создание таблицы Объявлений
CREATE TABLE IF NOT EXISTS advert
(
    id     bigint        PRIMARY KEY,
    users bigint NOT NULL,
    image  varchar(32),
    price  integer NOT NULL,
    title  varchar(32)
);

--Создание таблицы Объявление комментарии
CREATE TABLE IF NOT EXISTS comment
(
    id     bigint PRIMARY KEY,
    users integer NOT NULL,
    createdate  varchar(32),
    text  varchar(32)
);

--Создание таблицы Пользователи
CREATE TABLE IF NOT EXISTS users
(
    id     bigserial PRIMARY KEY,
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
CREATE TABLE IF NOT EXISTS image
(
    id     bigint PRIMARY KEY,
    filePath  varchar(32),
    fileSize  bigint,
    mediaType   varchar(32),
    "data"      oid
);

CREATE TABLE IF NOT EXISTS authorities (
      username varchar(50) not null,
      authority varchar(50) not null
);

Select * from advert;




