package com.edtech_api.model_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.edtech_api.domain.model.Bootcamp;
import com.edtech_api.domain.model.Carreira;
import com.edtech_api.domain.model.enums.StatusBootcamp;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@ActiveProfiles("test")
public class BootcampTest {

	private Bootcamp bootcamp;
	private Carreira carreira;
	
	@BeforeAll
	public void estanciaObjetoBootcamp() {
		carreira = new Carreira("Java Full Stack", 16, 4);
		bootcamp = new Bootcamp("Developer's Java Full Stack", 
				carreira, LocalDate.of(2022, 8, 26), LocalDate.of(2022, 12, 15), StatusBootcamp.ABERTA);
	}
	
	@Test
	public void verificaEstanciacaoBootcamp() {
		assertEquals("Developer's Java Full Stack", bootcamp.getNome());
		assertEquals("Java Full Stack", bootcamp.getCarreira().getNome());
		assertEquals(16, bootcamp.getCarreira().getDuracao());
		assertEquals(4, bootcamp.getCarreira().getDuracaoProjetoFinal());
		assertEquals(LocalDate.of(2022, 8, 26), bootcamp.getDataInicio());
		assertEquals(LocalDate.of(2022, 12, 15), bootcamp.getDataTermino());
		assertEquals(StatusBootcamp.ABERTA, bootcamp.getStatus());
	}
}
