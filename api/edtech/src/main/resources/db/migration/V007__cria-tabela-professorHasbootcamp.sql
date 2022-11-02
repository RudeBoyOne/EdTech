CREATE TABLE professor_has_bootcamp (
	id_professor				BIGINT	NOT NULL,
    id_bootcamp				BIGINT	NOT NULL,
	CONSTRAINT fk_ProfessorHasBootcamp		FOREIGN KEY(id_professor)	REFERENCES professor(id),
	CONSTRAINT fk_Bootcamp		FOREIGN KEY(id_bootcamp)	REFERENCES bootcamp(id)
);