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

import com.edtech_api.domain.model.Bootcamp;
import com.edtech_api.domain.model.Carreira;
import com.edtech_api.domain.model.enums.StatusBootcamp;
import com.edtech_api.domain.service.BootcampService;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@ActiveProfiles("test")
public class BootcampServiceTest {

	@Autowired
	private BootcampService bootcampService;
	
	private Carreira carreira;
	
	private Bootcamp bootcamp;
	
	private Bootcamp bootcampSalvo;
	
	
	@BeforeAll
	private void createAndSaveExtensao() {
		carreira = new Carreira("Web FullStack", 16, 4);
		bootcamp = new Bootcamp("Java", carreira, LocalDate.of(2022, 8, 20), 
				LocalDate.of(2022, 10, 10), StatusBootcamp.ABERTA);
		
		bootcampSalvo = bootcampService.criarBootcamp(bootcamp);
		
		assertEquals("Java", bootcampSalvo.getNome());
		assertEquals("Web FullStack", bootcampSalvo.getCarreira().getNome());
		assertEquals(LocalDate.of(2022, 8, 20), bootcampSalvo.getDataInicio());
		assertEquals(LocalDate.of(2022, 10, 10), bootcampSalvo.getDataTermino());
		assertEquals(StatusBootcamp.ABERTA, bootcampSalvo.getStatus());
	}
	
	@Test
	public void updateBootcamp() {
		bootcamp.setNome("Angular");
		bootcamp.setDataInicio(LocalDate.of(2023, 1, 14));
		bootcamp.setDataTermino(LocalDate.of(2023, 4, 24));
		
		bootcampSalvo = bootcampService.criarBootcamp(bootcamp);
		
		assertEquals("Angular", bootcampSalvo.getNome());
		assertEquals("Web FullStack", bootcampSalvo.getCarreira().getNome());
		assertEquals(LocalDate.of(2023, 1, 14), bootcampSalvo.getDataInicio());
		assertEquals(LocalDate.of(2023, 4, 24), bootcampSalvo.getDataTermino());
		assertEquals(StatusBootcamp.ABERTA, bootcampSalvo.getStatus());
	}
	
	@Test
	public void searchBootcampById() {
		assertEquals("Angular", bootcampService.buscarBootcampById(bootcampSalvo.getIdBootcamp()).getNome());
		assertEquals("Web FullStack", bootcampService.buscarBootcampById(bootcampSalvo.getIdBootcamp()).getCarreira().getNome());
		assertEquals(LocalDate.of(2023, 1, 14), bootcampService.buscarBootcampById(bootcampSalvo.getIdBootcamp()).getDataInicio());
		assertEquals(LocalDate.of(2023, 4, 24), bootcampService.buscarBootcampById(bootcampSalvo.getIdBootcamp()).getDataTermino());		
		assertEquals(StatusBootcamp.ABERTA, bootcampService.buscarBootcampById(bootcampSalvo.getIdBootcamp()).getStatus());		
	}
	
	@Test
	public void listAllBootcamps() {
		assertEquals(1, bootcampService.listarTodosBootcamps().size());
	}
	
	@Test
	public void deleteBootcampById() {
		bootcampService.deletarBootcampById(bootcampSalvo.getIdBootcamp());
		
		assertFalse(bootcampService.existeBootcampById(bootcampSalvo.getIdBootcamp()));
		bootcampSalvo = bootcampService.criarBootcamp(bootcamp);
	}
}
