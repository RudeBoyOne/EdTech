package com.edtech_api.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.edtech_api.domain.model.Carreira;
import com.edtech_api.domain.service.CarreiraService;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarreiraServiceTest {

	@Autowired
	private CarreiraService carreiraService;

	private Carreira carreira;

	private Carreira carreiraSalva;
	
	@BeforeAll
	public void createAndSaveCarreira() {
		carreira = new Carreira("Java Web FullStack", 16, 4);
		
		carreiraSalva = carreiraService.criarCarreira(carreira);	
		
		assertEquals("Java Web FullStack", carreiraSalva.getNome());
		assertEquals(16, carreiraSalva.getDuracao());
		assertEquals(4, carreiraSalva.getDuracaoProjetoFinal());
	}


	@Test
	public void updateCarreira() {
		carreira.setNome("Angular FrontEnd");
		carreira.setDuracao(18);
		carreira.setDuracaoProjetoFinal(5);
		carreiraSalva = carreiraService.criarCarreira(carreira);

		assertEquals("Angular FrontEnd", carreiraSalva.getNome());
		assertEquals(18, carreiraSalva.getDuracao());
		assertEquals(5, carreiraSalva.getDuracaoProjetoFinal());
	}

	@Test
	public void searchCarreiraById() {
		assertEquals("Java Web FullStack", carreiraService.buscarCarreiraById(carreiraSalva.getIdCarreira()).getNome());
		assertEquals(16, carreiraService.buscarCarreiraById(carreiraSalva.getIdCarreira()).getDuracao());
		assertEquals(4, carreiraService.buscarCarreiraById(carreiraSalva.getIdCarreira()).getDuracaoProjetoFinal());

	}
	
	@Test
	public void deleteCarreiraById() {
		carreiraService.deletarCarreiraById(carreiraSalva.getIdCarreira());
		
		assertFalse(carreiraService.existeCarreiraById(carreiraSalva.getIdCarreira()));
	}
}
