CREATE TABLE professor_has_bootcamp (
	idProfessor				BIGINT	NOT NULL,
    idBootcamp				BIGINT	NOT NULL,
	CONSTRAINT fk_ProfessorHasBootcamp		FOREIGN KEY(idProfessor)	REFERENCES professor(id),
	CONSTRAINT fk_Bootcamp		FOREIGN KEY(idBootcamp)	REFERENCES bootcamp(id)
);