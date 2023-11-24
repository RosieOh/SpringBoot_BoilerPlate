SHOW DATABASES;

CREATE DATABASE estore;

USE estore;

SHOW TABLES;

SELECT * FROM board;
DESC board;

DROP TABLE board;

SELECT * FROM board;

USE edutstore;

SHOW TABLES;

UPDATE board SET regdate=CURRENT_TIMESTAMP();

DROP TABLE board;

SELECT * FROM board;