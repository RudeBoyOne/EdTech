package com.edtech_api.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.edtech_api.domain.model.Carreira;
import com.edtech_api.domain.model.HabilidadeTecnica;
import com.edtech_api.domain.model.Professor;
import com.edtech_api.domain.service.ProfessorService;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@ActiveProfiles("test")
public class ProfessorServiceTest {

	@Autowired
	private ProfessorService professorService;
	
	private HabilidadeTecnica habilidadeTecnica;
	
	private List<HabilidadeTecnica> habilidadesTecnicas = new ArrayList<>();
 	
	private Carreira carreira;
	
	private Professor professor;
	
	private Professor professorSalvo;
	
	@BeforeAll
	public void createAndSaveProfessor() {
		habilidadeTecnica = new HabilidadeTecnica("Angular");
		habilidadesTecnicas.add(habilidadeTecnica);
		carreira = new Carreira("Java Web FullStack", 16, 4);
		professor = new Professor("Lucas", "lucas@gmail.com", carreira, habilidadesTecnicas);
		
		professorSalvo = professorService.criarProfessor(professor);
	}
	
	@Test
	public void updateProfessor() {
		habilidadeTecnica = new HabilidadeTecnica("React");
		habilidadesTecnicas.add(habilidadeTecnica);
		carreira = new Carreira("FrontEnd Full", 16, 4);
		professor.setNome("João");
		professor.setEmail("joao@gmail.com");
		professor.setHabilidadesTecnicas(habilidadesTecnicas);
		professor.setCarreira(carreira);
		
		professorSalvo = professorService.criarProfessor(professor);

		assertEquals("João", professorSalvo.getNome());
		assertEquals("joao@gmail.com", professorSalvo.getEmail());
		assertEquals("FrontEnd Full", professorSalvo.getCarreira().getNome());
		assertEquals("React", professorSalvo.getHabilidadesTecnicas().get(1).getNome());
	}
	
	@Test
	public void searchProfessorById() {
		professorSalvo = professorService.buscarProfessorById(professorSalvo.getIdProfessor());
		
		assertEquals("Lucas", professorSalvo.getNome());
		assertEquals("lucas@gmail.com", professorSalvo.getEmail());
		assertEquals("Java Web FullStack", professorSalvo.getCarreira().getNome());
		assertEquals("Angular", professorSalvo.getHabilidadesTecnicas().get(0).getNome());
	}
	
	@Test
	public void listAllProfessores() {
		assertEquals(1, professorService.listarTodosProfessores().size());
	}
	
	@Test
	public void deleteProfessorById() {
		professorService.deletarProfessorById(professorSalvo.getIdProfessor());
		
		assertFalse(professorService.existeProfessorById(professorSalvo.getIdProfessor()));
	}
}
