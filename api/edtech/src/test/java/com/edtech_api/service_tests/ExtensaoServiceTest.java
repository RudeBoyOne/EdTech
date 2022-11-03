package com.edtech_api.service_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.edtech_api.domain.model.Extensao;
import com.edtech_api.domain.service.ExtensaoService;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@ActiveProfiles("test")
public class ExtensaoServiceTest {

	@Autowired
	private ExtensaoService extensaoService;
	
	private Extensao extensao;
	
	private Extensao extensaoSalva;
	
	@BeforeAll
	public void createAndSaveExtensao() {
		extensao = new Extensao("Dockerização", "Docker", LocalDate.of(2022, 8, 20), 
				LocalDate.of(2022, 10, 10));
		
		extensaoSalva = extensaoService.criarExtensao(extensao);
		
		assertEquals("Dockerização", extensaoSalva.getNome());
		assertEquals("Docker", extensaoSalva.getEspecialidade());
		assertEquals(LocalDate.of(2022, 8, 20), extensaoSalva.getDataInicio());
		assertEquals(LocalDate.of(2022, 10, 10), extensaoSalva.getDataTermino());
	}
	
	
	@Test
	public void updateExtensao() {
		extensao.setNome("Kubernetes em ação");
		extensao.setEspecialidade("Kubernetes");
		
		extensaoSalva = extensaoService.criarExtensao(extensao);
		
		assertEquals("Kubernetes em ação", extensaoSalva.getNome());
		assertEquals("Kubernetes", extensaoSalva.getEspecialidade());
	}
	
	@Test
	public void searchExtensaoById() {
		extensaoSalva = extensaoService.criarExtensao(extensao);
		assertEquals("Kubernetes em ação", extensaoService.buscarExtensaoById(extensaoSalva.getIdExtensao()).getNome());
		assertEquals("Kubernetes", extensaoService.buscarExtensaoById(extensaoSalva.getIdExtensao()).getEspecialidade());
		assertEquals(LocalDate.of(2022, 8, 20), extensaoService.buscarExtensaoById(extensaoSalva.getIdExtensao()).getDataInicio());
		assertEquals(LocalDate.of(2022, 10, 10), extensaoService.buscarExtensaoById(extensaoSalva.getIdExtensao()).getDataTermino());
	}
	
	@Test
	public void listAllExtensao() {
		assertEquals(1, extensaoService.listarTodasExtensoes().size());
	}
	
	@Test
	public void deleteExtensaoById() {
		extensaoService.deletarExtensaoById(extensaoSalva.getIdExtensao());
		
		assertFalse(extensaoService.existeExtensaoById(extensaoSalva.getIdExtensao()));
	}
}
