package com.edtech_api.repositoryTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.edtech_api.domain.model.HabilidadeTecnica;
import com.edtech_api.domain.repository.IHabilidadeTecnicaRepository;

@DataJpaTest
@ActiveProfiles("test")
public class HabilidadeTecnicaTest {

	@Autowired
	private IHabilidadeTecnicaRepository habilidadeTecnicaRepository;
	
	private HabilidadeTecnica habilidadeTecnica;
	
	private HabilidadeTecnica habilidadeTecnicaSalva;
	
	@Test
	public void createAndSaveHabilidadeTecnica() {
		habilidadeTecnica = new HabilidadeTecnica("Java");
		habilidadeTecnicaSalva = habilidadeTecnicaRepository.save(habilidadeTecnica);
		assertEquals("Java", habilidadeTecnicaSalva.getNome());
	}
	
	@Test
	public void updateHabilidadeTecnica() {
		habilidadeTecnica = new HabilidadeTecnica("Java");
		habilidadeTecnicaRepository.save(habilidadeTecnica);
		habilidadeTecnica.setNome("Angular");
		habilidadeTecnicaSalva = habilidadeTecnicaRepository.saveAndFlush(habilidadeTecnica);
		assertEquals("Angular", habilidadeTecnicaSalva.getNome());
	}
	
	@Test
	public void searchHabilidadeTecnicaById() {
		habilidadeTecnica = new HabilidadeTecnica("Java");
		habilidadeTecnicaSalva = habilidadeTecnicaRepository.save(habilidadeTecnica);
		assertTrue(habilidadeTecnicaRepository.findById(habilidadeTecnicaSalva.getIdHabilidadeTecnica()).isPresent());
	}
	
	@Test
	public void deleteHabilidadeTecnicaById() {
		habilidadeTecnica = new HabilidadeTecnica("Java");
		habilidadeTecnicaSalva = habilidadeTecnicaRepository.save(habilidadeTecnica);
		habilidadeTecnicaRepository.deleteById(habilidadeTecnicaSalva.getIdHabilidadeTecnica());
		assertTrue(habilidadeTecnicaRepository.findById(habilidadeTecnicaSalva.getIdHabilidadeTecnica()).isEmpty());
	}
	
}
