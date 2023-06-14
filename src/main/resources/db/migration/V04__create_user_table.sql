CREATE TABLE USER
(
    id         BINARY(16) DEFAULT (UUID_TO_BIN(UUID())) PRIMARY KEY,
    name       VARCHAR(100)                                                     NOT NULL,
    email      VARCHAR(255)                                                     NOT NULL,
    password   VARCHAR(255)                                                     NOT NULL,
    type       VARCHAR(20)                                                      NOT NULL,
    active     BOOLEAN                                                          NOT NULL,
    dt_created DATETIME   DEFAULT CURRENT_TIMESTAMP                             NOT NULL,
    dt_updated DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;