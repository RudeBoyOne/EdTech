package com.edtech_api.service_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.edtech_api.domain.model.HabilidadeTecnica;
import com.edtech_api.domain.service.HabilidadeTecnicaService;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@ActiveProfiles("test")
public class HabilidadeTecnicaServiceTest {

	@Autowired
	private HabilidadeTecnicaService habilidadeTecnicaService;
	
	private HabilidadeTecnica habilidadeTecnica;
	
	private HabilidadeTecnica habilidadeTecnicaSalva;
	
	@BeforeAll
	public void createAndSaveHabilidadeTecnica() {
		habilidadeTecnica = new HabilidadeTecnica("Angular");
		habilidadeTecnicaSalva = habilidadeTecnicaService.criarHabilidadeTecnica(habilidadeTecnica);
	}
	
	@Test
	public void updateHabilidadeTecnica() {
		habilidadeTecnica.setNome("React");
		
		habilidadeTecnicaSalva = habilidadeTecnicaService.criarHabilidadeTecnica(habilidadeTecnica);
		
		assertEquals("React", habilidadeTecnicaSalva.getNome());
	}
	
	@Test
	public void searchHabilidadeTecnicaById() {
		habilidadeTecnicaSalva = habilidadeTecnicaService.criarHabilidadeTecnica(habilidadeTecnica);
		assertEquals("Angular", habilidadeTecnicaService.buscarHabilidadeTecnicaById(habilidadeTecnicaSalva.getIdHabilidadeTecnica()).getNome());
	}
	
	@Test
	public void listAllHabilidadesTecnicas() {
		assertEquals(2, habilidadeTecnicaService.listarTodasHabilidadesTecnicas().size());
	}
	
	@Test
	public void deleteHabilidadeTecnicaById() {
		habilidadeTecnicaService.deletarHabilidadeTecnicaById(habilidadeTecnicaSalva.getIdHabilidadeTecnica());
		
		assertFalse(habilidadeTecnicaService.existeHabilidadeTecnicaById(habilidadeTecnicaSalva.getIdHabilidadeTecnica()));
	}
}
