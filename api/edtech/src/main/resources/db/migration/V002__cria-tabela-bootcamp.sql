CREATE TABLE bootcamp (
	id					BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome				VARCHAR(60) NOT NULL,
    data_inicio			DATE NOT NULL,
    data_termino		DATE NOT NULL,
    id_carreira			BIGINT NOT NULL,
    CONSTRAINT fk_Carreira	FOREIGN KEY(id_carreira)  REFERENCES carreira(id)
);