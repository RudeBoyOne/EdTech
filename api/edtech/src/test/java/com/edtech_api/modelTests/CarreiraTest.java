package com.edtech_api.modelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;

import com.edtech_api.domain.model.Carreira;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
public class CarreiraTest {

	private Carreira carreira;
	
	//com o uso do BeforeAll precisamos anotar a class com a annotation @TestInstance(Lifecycle.PER_CLASS) para o teste rodar
	@BeforeAll
	public void estanciaObjetoCarreira() {
		carreira = new Carreira ("Java Web Full Stack", 16, 4);
	}
	
	@Test
	public void verificaEstanciacaoCarreira() {
		assertEquals("Java Web Full Stack", carreira.getNome());
		assertEquals(16, carreira.getDuracao());
		assertEquals(4, carreira.getDuracaoProjetoFinal());
	}
}
