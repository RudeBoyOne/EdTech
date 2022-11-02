CREATE TABLE extensao (
	id					BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome				VARCHAR(60) NOT NULL,
    especialidade 		VARCHAR(100) NOT NULL,
    data_inicio			DATE NOT NULL,
    data_termino		DATE NOT NULL
);