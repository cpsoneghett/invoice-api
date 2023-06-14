CREATE TABLE ENTRY
(
    id          BINARY(16) DEFAULT (UUID_TO_BIN(UUID())) PRIMARY KEY,
    description VARCHAR(50)                                                      NOT NULL,
    dt_due      DATE                                                             NOT NULL,
    dt_payment  DATETIME,
    value       DECIMAL(10, 2)                                                   NOT NULL,
    note        VARCHAR(100),
    type        VARCHAR(20)                                                      NOT NULL,
    id_category BIGINT(20)                                                       NOT NULL,
    id_person   BINARY(16)                                                       NOT NULL,
    dt_created  DATETIME   DEFAULT CURRENT_TIMESTAMP                             NOT NULL,
    dt_updated  DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (id_category) REFERENCES CATEGORY (id),
    FOREIGN KEY (id_person) REFERENCES PERSON (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

