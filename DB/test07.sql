CREATE DATABASE tsherpa;

USE tsherpa;

CREATE TABLE role(
role_id INT PRIMARY KEY AUTO_INCREMENT,
role VARCHAR(255) DEFAULT NULL);

CREATE TABLE user(
user_id INT PRIMARY KEY AUTO_INCREMENT,
active INT DEFAULT 0, 
login_id VARCHAR(255) NOT NULL,
user_name VARCHAR(255) NOT NULL,
password VARCHAR(300) NOT NULL
);

CREATE TABLE user_role(
	user_id INT NOT NULL,
	role_id INT NOT NULL,
	PRIMARY KEY (user_id, role_id)
);

DESC ROLE;

DESC USER;

DESC user_role;

DROP TABLE USER;
