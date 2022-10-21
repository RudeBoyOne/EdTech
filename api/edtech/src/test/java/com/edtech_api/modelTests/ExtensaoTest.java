package com.edtech_api.modelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.edtech_api.domain.model.Extensao;

@SpringBootTest
public class ExtensaoTest {
	
	private Extensao extensao;
	
	// com o beforeEach não há necessidade de colocarmos a annotation @TestInstance(Lifecycle.PER_CLASS) para rodar o teste
	@BeforeEach
	public void estanciaObjetoExtensao() {
		extensao = new Extensao("API's Rest", "Spring Boot", LocalDate.of(2022, 6, 17) , LocalDate.of(2022, 7, 17));
	}
	
	@Test
	public void verificaEstanciacaoExtensao() {
		assertEquals("API's Rest", extensao.getNome());
		assertEquals("Spring Boot", extensao.getEspecialidade());
		assertEquals(LocalDate.of(2022, 6, 17), extensao.getDataInicio());
		assertEquals(LocalDate.of(2022, 7, 17), extensao.getDataTermino());
	}

}
