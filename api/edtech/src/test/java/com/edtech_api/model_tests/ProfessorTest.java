package com.edtech_api.model_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.edtech_api.domain.model.Carreira;
import com.edtech_api.domain.model.HabilidadeTecnica;
import com.edtech_api.domain.model.Professor;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@ActiveProfiles("test")
public class ProfessorTest {

	private Professor professor;
	
	private Carreira carreira;
	
	private HabilidadeTecnica habilidadeTecnica; 
	
	private List<HabilidadeTecnica> habilidadeTecnicas = new ArrayList<>();
	
	@BeforeAll
	public void estanciaObjetoProfessor() {
		carreira = new Carreira("Java Web Full Stack", 16, 4);
		habilidadeTecnica = new HabilidadeTecnica("Angular");
		habilidadeTecnicas.add(habilidadeTecnica);		
		professor =  new Professor("Lucas", "lucas@gmail.com", carreira, habilidadeTecnicas);
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
