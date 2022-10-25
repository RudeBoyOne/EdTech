CREATE TABLE professor_has_habilidade (
	idProfessor				BIGINT NOT NULL,
    idHabilidadeTecnica		BIGINT NOT NULL,
    CONSTRAINT fk_ProfessorHasHabilidade		FOREIGN KEY(idProfessor)	REFERENCES professor(id),
    CONSTRAINT fk_HabilidadeTecnica		FOREIGN KEY(idHabilidadeTecnica)	REFERENCES habilidade_tecnica(id)
);