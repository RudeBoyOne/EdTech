CREATE TABLE professor_has_habilidade (
	id_professor				BIGINT NOT NULL,
    id_habilidadeTecnica		BIGINT NOT NULL,
    CONSTRAINT fk_ProfessorHasHabilidade		FOREIGN KEY(id_professor)	REFERENCES professor(id),
    CONSTRAINT fk_HabilidadeTecnica		FOREIGN KEY(id_habilidadeTecnica)	REFERENCES habilidade_tecnica(id)
);