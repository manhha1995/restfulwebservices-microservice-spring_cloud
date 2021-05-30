drop table if exists users;

CREATE TABLE if not exists users(
    ID INT NOT NULL,
    NAME VARCHAR(100),
    DATE_BIRTH TIMESTAMP,
    PRIMARY KEY(ID)
)
