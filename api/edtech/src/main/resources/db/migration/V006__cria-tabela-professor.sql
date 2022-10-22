CREATE TABLE professor (
	id						BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome					VARCHAR(60) NOT NULL,
    email					VARCHAR(100) NOT NULL,
    idCarreira				BIGINT NOT NULL,
    idExtensao				BIGINT NOT NULL,
    CONSTRAINT fk_Carreira_Professor	FOREIGN KEY(idCarreira)  REFERENCES carreira(id),
    CONSTRAINT fk_Extensao	FOREIGN KEY(idExtensao)  REFERENCES extensao(id)
);