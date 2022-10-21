package com.edtech_api.modelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;

import com.edtech_api.domain.model.HabilidadeTecnica;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
public class HabilidadeTecnicaTest {
	
	private HabilidadeTecnica habilidadeTecnica;
	
	@BeforeAll
	public void estanciaObjetoHabilidadeTecnica() {
		habilidadeTecnica = new HabilidadeTecnica("Java");
	}
	
	@Test
	public void verificaEstanciacaoHabilidadeTecnica() {
		assertEquals("Java", habilidadeTecnica.getNome());
	}

}
