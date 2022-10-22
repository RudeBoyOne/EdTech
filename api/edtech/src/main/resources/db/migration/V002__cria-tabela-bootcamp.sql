CREATE TABLE bootcamp (
	id				BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome			VARCHAR(60) NOT NULL,
    dataInicio		DATE NOT NULL,
    dataTermino		DATE NOT NULL,
    idCarreira		BIGINT NOT NULL,
    CONSTRAINT fk_Carreira	FOREIGN KEY(idCarreira)  REFERENCES carreira(id)
);