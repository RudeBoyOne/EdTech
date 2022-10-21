package com.edtech_api.modelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;

import com.edtech_api.domain.model.Bootcamp;
import com.edtech_api.domain.model.Carreira;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
public class BootcampTest {

	private Bootcamp bootcamp;
	private Carreira carreira;
	
	@BeforeAll
	public void estanciaObjetoBootcamp() {
		carreira = new Carreira("Java Full Stack", 16, 4);
		bootcamp = new Bootcamp("Developer's Java Full Stack", 
				carreira, LocalDate.of(2022, 8, 26), LocalDate.of(2022, 12, 15));
	}
	
	@Test
	public void verificaEstanciacaoBootcamp() {
		assertEquals("Developer's Java Full Stack", bootcamp.getNome());
		assertEquals("Java Full Stack", bootcamp.getCarreira().getNome());
		assertEquals(16, bootcamp.getCarreira().getDuracao());
		assertEquals(4, bootcamp.getCarreira().getDuracaoProjetoFinal());
		assertEquals(LocalDate.of(2022, 8, 26), bootcamp.getDataInicio());
		assertEquals(LocalDate.of(2022, 12, 15), bootcamp.getDataTermino());
	}
}
