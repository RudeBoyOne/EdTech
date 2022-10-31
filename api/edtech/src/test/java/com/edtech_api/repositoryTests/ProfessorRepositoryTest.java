package com.edtech_api.repositoryTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.edtech_api.domain.model.Carreira;
import com.edtech_api.domain.model.HabilidadeTecnica;
import com.edtech_api.domain.model.Professor;
import com.edtech_api.domain.repository.IProfessorRepository;

@DataJpaTest
@ActiveProfiles("test")
public class ProfessorRepositoryTest {

	@Autowired
	private IProfessorRepository professorRepository;

	private Carreira carreira;

	private HabilidadeTecnica habilidadeTecnica;

	List<HabilidadeTecnica> habilidadeTecnicas = new ArrayList<>();

	private Professor professor;

	private Professor professorSalvo;
	
	@BeforeEach
	public void estanciaObjetoProfessorParaTest() {
		habilidadeTecnica = new HabilidadeTecnica("Java");
		habilidadeTecnicas.add(habilidadeTecnica);
		carreira = new Carreira("Web Full Stack", 16, 4);
		professor = new Professor("Lucas", "lucas@gmail.com", carreira, habilidadeTecnicas);		
	}

	@Test
	public void createAndSaveProfessor() {
		professorSalvo = professorRepository.save(professor);
		assertEquals("Lucas", professorSalvo.getNome());
		assertEquals("lucas@gmail.com", professorSalvo.getEmail());
		assertEquals("Web Full Stack", professorSalvo.getCarreira().getNome());
		assertFalse(professorSalvo.getHabilidadesTecnicas().isEmpty());
	}
	
	@Test
	public void updateProfessor() {
		professorRepository.save(professor);
		professor.setNome("Josisvaldo");
		professor.setEmail("josisvaldo@gmail.com");
		habilidadeTecnicas.removeAll(habilidadeTecnicas);
		professor.setHabilidadesTecnicas(habilidadeTecnicas);
		
		professorSalvo = professorRepository.saveAndFlush(professor);
		
		assertEquals("Josisvaldo", professorSalvo.getNome());
		assertEquals("josisvaldo@gmail.com", professorSalvo.getEmail());
		assertEquals("Web Full Stack", professorSalvo.getCarreira().getNome());
		assertTrue(professorSalvo.getHabilidadesTecnicas().isEmpty());
	}
	
	@Test
	public void searchProfessorById() {
		professorSalvo = professorRepository.save(professor);
		assertTrue(professorRepository.findById(professorSalvo.getIdProfessor()).isPresent());
	}
	
	@Test
	public void deleteProfessorById() {
		professorSalvo = professorRepository.save(professor);
		professorRepository.deleteById(professorSalvo.getIdProfessor());
		assertTrue(professorRepository.findById(professorSalvo.getIdProfessor()).isEmpty());
	}
}
