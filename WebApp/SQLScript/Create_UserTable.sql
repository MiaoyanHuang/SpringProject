CREATE TABLE user
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    email      VARCHAR(255),
    username   VARCHAR(100) UNIQUE NOT NULL,
    avatar     LONGBLOB,
    firstName  VARCHAR(100),
    lastName   VARCHAR(100),
    password   VARCHAR(255)        NOT NULL,
    identity   VARCHAR(50),
    gender     VARCHAR(10),
    createTime DATETIME DEFAULT CURRENT_TIMESTAMP,
    updateTime DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

DROP TABLE user;
