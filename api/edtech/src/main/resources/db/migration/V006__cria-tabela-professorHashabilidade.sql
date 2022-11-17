CREATE TABLE professor_has_habilidade (
	id_professor				BIGINT NOT NULL,
    id_habilidade_tecnica		BIGINT NOT NULL,
    CONSTRAINT fk_ProfessorHasHabilidade		FOREIGN KEY(id_professor)	REFERENCES professor(id),
    CONSTRAINT fk_HabilidadeTecnica		FOREIGN KEY(id_habilidade_tecnica)	REFERENCES habilidade_tecnica(id)
);