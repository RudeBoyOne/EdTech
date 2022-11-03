package com.edtech_api.repository_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.edtech_api.domain.model.Bootcamp;
import com.edtech_api.domain.model.Carreira;
import com.edtech_api.domain.repository.IBootcampRepository;


@DataJpaTest
@ActiveProfiles("test")
public class BootcampRepositoryTest {

	@Autowired
	private IBootcampRepository bootcampRepository;

	private Carreira carreira;

	private Bootcamp bootcamp;

	private Bootcamp bootcampSalvo;
	
	@BeforeEach
	public void estanciaObjetosBootcampParaTest() {
		carreira = new Carreira("Java", 16, 4);
		bootcamp = new Bootcamp("Java Web FullStack", carreira, LocalDate.of(2022, 8, 10), LocalDate.of(2022, 11, 15));		
	}

	@Test 	
	public void createAndSaveBootcamp() {
		bootcampSalvo = bootcampRepository.save(bootcamp);
		assertEquals("Java Web FullStack", bootcampSalvo.getNome());
		assertEquals(LocalDate.of(2022, 8, 10), bootcampSalvo.getDataInicio());
		assertEquals(LocalDate.of(2022, 11, 15), bootcampSalvo.getDataTermino());
		assertEquals("Java", bootcampSalvo.getCarreira().getNome());
		assertEquals(16, bootcampSalvo.getCarreira().getDuracao());
		assertEquals(4, bootcampSalvo.getCarreira().getDuracaoProjetoFinal());
	}

	@Test
	public void updateBootcamp() {
		bootcampRepository.save(bootcamp);
		bootcamp.setNome("Pyton");
		bootcamp.setDataInicio(LocalDate.of(2023, 1, 1));
		bootcamp.setDataTermino(LocalDate.of(2023, 4, 20));
		bootcampSalvo = bootcampRepository.saveAndFlush(bootcamp);
		assertEquals("Pyton", bootcampSalvo.getNome());
		assertEquals(LocalDate.of(2023, 1, 1), bootcampSalvo.getDataInicio());
		assertEquals(LocalDate.of(2023, 4, 20), bootcampSalvo.getDataTermino());
	}

	@Test
	public void searchOneBootcampById() {
		bootcampSalvo = bootcampRepository.save(bootcamp);
		assertTrue(bootcampRepository.findById(bootcampSalvo.getIdBootcamp()).isPresent());
	}

	@Test
	public void deleteOneCarreiraById() {
		bootcampSalvo = bootcampRepository.save(bootcamp);
		bootcampRepository.deleteById(bootcampSalvo.getIdBootcamp());
		assertTrue(bootcampRepository.findById(bootcampSalvo.getIdBootcamp()).isEmpty());
	}

}
