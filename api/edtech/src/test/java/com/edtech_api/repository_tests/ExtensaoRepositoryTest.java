package com.edtech_api.repository_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.edtech_api.domain.model.Extensao;
import com.edtech_api.domain.repository.IExtensaoRepository;

@DataJpaTest
@ActiveProfiles("test")
public class ExtensaoRepositoryTest {

	@Autowired
	private IExtensaoRepository extensaoRepository;
	
	private Extensao extensao;
	
	private Extensao extensaoSalva;
	
	@BeforeEach
	public void estanciaObjetosExtensaoParaTest() {
		extensao = new Extensao("Docker Fire", "Docker", LocalDate.of(2022, 3, 10), LocalDate.of(2022, 5, 20));		
	}
	
	@Test
	public void createAndSaveExtensao() {
		extensaoSalva = extensaoRepository.save(extensao);
		assertEquals("Docker Fire", extensaoSalva.getNome());
		assertEquals("Docker", extensaoSalva.getEspecialidade());
		assertEquals(LocalDate.of(2022, 3, 10), extensao.getDataInicio());
		assertEquals(LocalDate.of(2022, 5, 20), extensao.getDataTermino());
	}
	
	@Test
	public void updateExtensao() {
		extensaoRepository.save(extensao);
		extensao.setNome("Kubernetes Fire");
		extensao.setEspecialidade("Kubernetes");
		extensao.setDataInicio(LocalDate.of(2022, 8, 01));
		extensao.setDataTermino(LocalDate.of(2022, 10, 05));
		extensaoSalva = extensaoRepository.saveAndFlush(extensao);
		assertEquals("Kubernetes Fire", extensaoSalva.getNome());
		assertEquals("Kubernetes", extensaoSalva.getEspecialidade());
		assertEquals(LocalDate.of(2022, 8, 01), extensao.getDataInicio());
		assertEquals(LocalDate.of(2022, 10, 05), extensao.getDataTermino());
	}
	
	@Test
	public void searchOneExtensaoById() {
		extensaoSalva = extensaoRepository.save(extensao);
		assertTrue(extensaoRepository.findById(extensao.getIdExtensao()).isPresent());
	}
	
	@Test
	public void deleteOneExtensaoById() {
		extensaoSalva = extensaoRepository.save(extensao);
		extensaoRepository.deleteById(extensaoSalva.getIdExtensao());
		assertTrue(extensaoRepository.findById(extensaoSalva.getIdExtensao()).isEmpty());
	}
	
	

}
