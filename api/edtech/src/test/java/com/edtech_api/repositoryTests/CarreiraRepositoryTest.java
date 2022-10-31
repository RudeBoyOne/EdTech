package com.edtech_api.repositoryTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.edtech_api.domain.model.Carreira;
import com.edtech_api.domain.repository.ICarreiraRepository;


@DataJpaTest
@ActiveProfiles("test")
public class CarreiraRepositoryTest {
	
	@Autowired
	private ICarreiraRepository carreiraRepository;
	
	private Carreira carreira;
	
	private Carreira carreiraSalva;
	
	@BeforeEach
	public void criaSalvaCarreira() {
		carreira = new Carreira("Java Full Stack", 16, 4);
	}
	
	@Test
	public void verificaCarreiraSalva() {
		carreiraSalva = carreiraRepository.save(carreira);		
		assertEquals(carreiraSalva.getNome(), carreira.getNome());
		assertEquals(carreiraSalva.getDuracao(), carreira.getDuracao());
		assertEquals(carreiraSalva.getDuracaoProjetoFinal(), carreira.getDuracaoProjetoFinal());
	}
	
	@Test
	public void atualizaCarreira() {
		carreiraSalva = carreiraRepository.save(carreira);		
		carreira.setNome("Pyton");
		carreira.setDuracao(20); 
		carreira.setDuracaoProjetoFinal(5);
		carreiraSalva = carreiraRepository.saveAndFlush(carreira);
		assertEquals(carreiraSalva.getNome(), carreira.getNome());
		assertEquals(carreiraSalva.getDuracao(), carreira.getDuracao());
		assertEquals(carreiraSalva.getDuracaoProjetoFinal(), carreira.getDuracaoProjetoFinal());		
	}
	
	@Test
	public void buscaUmaCarreiraById() {
		carreiraSalva = carreiraRepository.save(carreira);		
		assertTrue(carreiraRepository.findById(carreiraSalva.getIdCarreira()).isPresent());
	}
	
	@Test
	public void deletaUmaCarreiraById() {
		carreiraSalva = carreiraRepository.save(carreira);		
		carreiraRepository.deleteById(carreiraSalva.getIdCarreira());
		assertTrue(carreiraRepository.findById(carreiraSalva.getIdCarreira()).isEmpty());
	}
}
