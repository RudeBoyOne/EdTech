package com.edtech_api.modelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;

import com.edtech_api.domain.model.Carreira;
import com.edtech_api.domain.model.Professor;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
public class ProfessorTest {

	private Professor professor;
	private Carreira carreira;
	
	@BeforeAll
	public void estanciaObjetoProfessor() {
		carreira = new Carreira("Java Web Full Stack", 16, 4);
		professor =  new Professor("Lucas", "lucas@gmail.com", carreira);
	}
	
	@Test
	public void verificaEstanciacaoProfessor() {
		assertEquals("Lucas", professor.getNome());
		assertEquals("lucas@gmail.com", professor.getEmail());
		assertEquals("Java Web Full Stack", professor.getCarreira().getNome());
		assertEquals(16, professor.getCarreira().getDuracao());
		assertEquals(4, professor.getCarreira().getDuracaoProjetoFinal());
	}
}
