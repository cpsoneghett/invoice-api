CREATE TABLE ENTRY (
    id BINARY(16) DEFAULT (UUID_TO_BIN(UUID())) PRIMARY KEY,
    description VARCHAR(50) NOT NULL,
    dt_due DATE NOT NULL,
    dt_payment DATE,
    value DECIMAL(10,2) NOT NULL,
    note VARCHAR(100),
    type VARCHAR(20) NOT NULL,
    id_category BIGINT(20) NOT NULL,
    id_person BINARY(16) NOT NULL,
    FOREIGN KEY (id_category) REFERENCES CATEGORY(id),
    FOREIGN KEY (id_person) REFERENCES PERSON(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*
INSERT INTO entry (description, dt_due, dt_payment, value, note, type, id_category, id_person) values ('Monthly Payment', '2017-06-10', null, 6500.00, 'Profit Distribution', 'INCOME', 1, UNHEX(REPLACE('YOUR_UUID_VALUE', '-', '')));
INSERT INTO entry (description, dt_due, dt_payment, value, note, type, id_category, id_person) values ('Bahamas', '2017-02-10', '2017-02-10', 100.32, null, 'EXPENSE', 2, UNHEX(REPLACE('YOUR_UUID_VALUE', '-', '')));
INSERT INTO entry (description, dt_due, dt_payment, value, note, type, id_category, id_person) values ('Top Club', '2017-06-10', null, 120, null, 'INCOME', 3, UNHEX(REPLACE('YOUR_UUID_VALUE', '-', '')));
INSERT INTO entry (description, dt_due, dt_payment, value, note, type, id_category, id_person) values ('CEMIG', '2017-02-10', '2017-02-10', 110.44, 'Generate', 'INCOME', 3, UNHEX(REPLACE('YOUR_UUID_VALUE', '-', '')));
INSERT INTO entry (description, dt_due, dt_payment, value, note, type, id_category, id_person) values ('DMAE', '2017-06-10', null, 200.30, null, 'EXPENSE', 3, UNHEX(REPLACE('YOUR_UUID_VALUE', '-', '')));
INSERT INTO entry (description, dt_due, dt_payment, value, note, type, id_category, id_person) values ('Extra', '2017-03-10', '2017-03-10', 1010.32, null, 'INCOME', 4, UNHEX(REPLACE('YOUR_UUID_VALUE', '-', '')));
INSERT INTO entry (description, dt_due, dt_payment, value, note, type, id_category, id_person) values ('Bahamas', '2017-06-10', null, 500, null, 'INCOME', 1, UNHEX(REPLACE('YOUR_UUID_VALUE', '-', '')));
INSERT INTO entry (description, dt_due, dt_payment, value, note, type, id_category, id_person) values ('Top Club', '2017-03-10', '2017-03-10', 400.32, null, 'EXPENSE', 4, UNHEX(REPLACE('YOUR_UUID_VALUE', '-', '')));
INSERT INTO entry (description, dt_due, dt_payment, value, note, type, id_category, id_person) values ('Dispatch', '2017-06-10', null, 123.64, 'Fines', 'EXPENSE', 3, UNHEX(REPLACE('YOUR_UUID_VALUE', '-', '')));
INSERT INTO entry (description, dt_due, dt_payment, value, note, type, id_category, id_person) values ('Tires', '2017-04-10', '2017-04-10', 665.33, null, 'INCOME', 5, UNHEX(REPLACE('YOUR_UUID_VALUE', '-', '')));
INSERT INTO entry (description, dt_due, dt_payment, value, note, type, id_category, id_person) values ('Coffee', '2017-06-10', null, 8.32, null, 'EXPENSE', 1, UNHEX(REPLACE('YOUR_UUID_VALUE', '-', '')));
INSERT INTO entry (description, dt_due, dt_payment, value, note, type, id_category, id_person) values ('Electronics', '2017-04-10', '2017-04-10', 2100.32, null, 'EXPENSE', 5, UNHEX(REPLACE('YOUR_UUID_VALUE', '-', '')));
INSERT INTO entry (description, dt_due, dt_payment, value, note, type, id_category, id_person) values ('Instruments', '2017-06-10', null, 1040.32, null, 'EXPENSE', 4, UNHEX(REPLACE('YOUR_UUID_VALUE', '-', '')));
INSERT INTO entry (description, dt_due, dt_payment, value, note, type, id_category, id_person) values ('Coffee', '2017-04-10', '2017-04-10', 4.32, null, 'EXPENSE', 4, UNHEX(REPLACE('YOUR_UUID_VALUE', '-', '')));
INSERT INTO entry (description, dt_due, dt_payment, value, note, type, id_category, id_person) values ('Snack', '2017-06-10', null, 10.20, null, 'EXPENSE', 4, UNHEX(REPLACE('YOUR_UUID_VALUE', '-', '')));
*/
