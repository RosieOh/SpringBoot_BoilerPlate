
##################################################

USE edumon;

CREATE TABLE user(id int PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(20) NOT NULL,
password VARCHAR(300) NOT NULL,
username VARCHAR(50) NOT NULL,
email VARCHAR(100) NOT NULL,
address VARCHAR(300),
tel VARCHAR(20),
regdate DATETIME DEFAULT CURRENT_TIMESTAMP,
lev VARCHAR(20) DEFAULT 'USER',
act VARCHAR(20) DEFAULT 'JOIN', 
CONSTRAINT key_name UNIQUE(NAME)
);

-- 더미 데이터
INSERT INTO user VALUES (DEFAULT, 'kim', '1234', '김', 'kim@gmail.com', '가산동', '010-1004-1004', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO user VALUES (DEFAULT, 'lee', '1234', '오', 'oh@gmail.com', '신림동', '010-7979-2848', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO user VALUES (DEFAULT, 'admin', '1234', '박세훈', 'park@gmail.com', '구로', '010-2424-7942', DEFAULT, DEFAULT, DEFAULT);

UPDATE user SET lev='ADMIN' WHERE NAME='admin';
UPDATE user SET lev='EMP' WHERE NAME='lee';

SELECT * FROM user;

UPDATE user SET PASSWORD='$2a$10$N4HrCSDECM/wNWqBGhzDMOrLN1Aw9WRHtmEqxuBK9sWJ3K97Jqau6';

COMMIT;

DESC user;

