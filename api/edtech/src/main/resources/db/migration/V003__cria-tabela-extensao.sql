CREATE TABLE extensao (
	id				BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome			VARCHAR(60) NOT NULL,
    especialidade 	VARCHAR(100) NOT NULL,
    dataInicio		DATE NOT NULL,
    dataTermino		DATE NOT NULL
);