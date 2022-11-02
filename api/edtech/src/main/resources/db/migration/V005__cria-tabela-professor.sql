CREATE TABLE professor (
	id						BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome					VARCHAR(60) NOT NULL,
    email					VARCHAR(100) NOT NULL,
    id_carreira				BIGINT NOT NULL,
    id_extensao				BIGINT NOT NULL,
    CONSTRAINT fk_Carreira_Professor	FOREIGN KEY(id_carreira)  REFERENCES carreira(id),
    CONSTRAINT fk_Extensao	FOREIGN KEY(id_extensao)  REFERENCES extensao(id)
);