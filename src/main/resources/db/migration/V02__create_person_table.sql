CREATE TABLE PERSON
(
    id           BINARY(16) DEFAULT (UUID_TO_BIN(UUID())) PRIMARY KEY,
    name         VARCHAR(50)                                                      NOT NULL,
    street       VARCHAR(30),
    number       VARCHAR(30),
    complement   VARCHAR(30),
    neighborhood VARCHAR(30),
    zip_code     VARCHAR(30),
    city         VARCHAR(30),
    state        VARCHAR(30),
    active       BOOLEAN                                                          NOT NULL,
    dt_created   DATETIME   DEFAULT CURRENT_TIMESTAMP                             NOT NULL,
    dt_updated   DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, active) values ('João Silva', 'Rua do Abacaxi', '10', null, 'Brasil', '38.400-12', 'Uberlândia', 'MG', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, active) values ('Maria Rita', 'Rua do Sabiá', '110', 'Apto 101', 'Colina', '11.400-12', 'Ribeirão Preto', 'SP', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, active) values ('Pedro Santos', 'Rua da Bateria', '23', null, 'Morumbi', '54.212-12', 'Goiânia', 'GO', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, active) values ('Ricardo Pereira', 'Rua do Motorista', '123', 'Apto 302', 'Aparecida', '38.400-12', 'Salvador', 'BA', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, active) values ('Josué Mariano', 'Av Rio Branco', '321', null, 'Jardins', '56.400-12', 'Natal', 'RN', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, active) values ('Pedro Barbosa', 'Av Brasil', '100', null, 'Tubalina', '77.400-12', 'Porto Alegre', 'RS', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, active) values ('Henrique Medeiros', 'Rua do Sapo', '1120', 'Apto 201', 'Centro', '12.400-12', 'Rio de Janeiro', 'RJ', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, active) values ('Carlos Santana', 'Rua da Manga', '433', null, 'Centro', '31.400-12', 'Belo Horizonte', 'MG', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, active) values ('Leonardo Oliveira', 'Rua do Músico', '566', null, 'Segismundo Pereira', '38.400-00', 'Uberlândia', 'MG', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, active) values ('Isabela Martins', 'Rua da Terra', '1233', 'Apto 10', 'Vigilato', '99.400-12', 'Manaus', 'AM', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, active) values ('João Silva', 'Rua do Abacaxi', '10', NULL, 'Brasil', '38.400-12', 'Uberlândia', 'MG', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, active) values ('Maria Santos', 'Avenida das Flores', '25', NULL, 'Centro', '15.500-09', 'São Paulo', 'SP', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, active) values ('Pedro Oliveira', 'Rua dos Pinheiros', '45', 'Apto 302', 'Jardim Europa', '50.200-14', 'Porto Alegre', 'RS', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, active) values ('Ana Souza', 'Travessa dos Girassóis', '7', NULL, 'Jardim Primavera', '20.400-35', 'Belo Horizonte', 'MG', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, active) values ('Carlos Rodrigues', 'Rua das Palmeiras', '80', 'Casa 2', 'Vila Nova', '35.700-21', 'Campinas', 'SP', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, active) values ('Fernanda Almeida', 'Alameda dos Ipês', '112', NULL, 'Centro', '11.200-05', 'São Paulo', 'SP', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, active) values ('Ricardo Lima', 'Avenida Beira-Mar', '235', 'Sala 1502', 'Praia da Barra', '22.100-17', 'Rio de Janeiro', 'RJ', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, active) values ('Lúcia Pereira', 'Rua das Violetas', '18', NULL, 'Jardim América', '75.300-08', 'Goiânia', 'GO', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, active) values ('José Costa', 'Travessa dos Lírios', '30', 'Apto 101', 'Centro', '18.900-20', 'Curitiba', 'PR', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, active) values ('Mariana Ferreira', 'Avenida das Acácias', '50', NULL, 'Jardim das Flores', '13.700-10', 'Salvador', 'BA', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, active) values ('Paulo Carvalho', 'Rua dos Cravos', '28', NULL, 'Jardim Botânico', '40.200-07', 'Recife', 'PE', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, active) values ('Amanda Santos', 'Alameda das Magnólias', '15', 'Casa 3', 'Vila Nova', '30.900-11', 'Porto Alegre', 'RS', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, active) values ('Rodrigo Oliveira', 'Rua das Orquídeas', '90', NULL, 'Jardim Primavera', '22.400-25', 'Belo Horizonte', 'MG', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, active) values ('Camila Rodrigues', 'Travessa das Palmeiras', '75', 'Apto 201', 'Centro', '30.700-18', 'Campinas', 'SP', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, active) values ('Guilherme Almeida', 'Alameda dos Girassóis', '135', NULL, 'Centro', '15.800-02', 'São Paulo', 'SP', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, active) values ('Juliana Lima', 'Avenida do Sol', '300', 'Sala 1201', 'Praia da Barra', '22.200-12', 'Rio de Janeiro', 'RJ', true);