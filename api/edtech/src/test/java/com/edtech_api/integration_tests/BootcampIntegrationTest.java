package com.edtech_api.integration_tests;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.edtech_api.api.dtos.inputs.BootcampInput;
import com.edtech_api.domain.model.enums.StatusBootcamp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class BootcampIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void criarBootcampTest() throws Exception {
		BootcampInput bootcampInput = new BootcampInput("BC13 Full Time", 1L, 
				LocalDate.of(2022, 10, 05), LocalDate.of(2023, 4, 15), StatusBootcamp.PROXIMATURMA.toString());
		
		
		ObjectWriter objectWriter = new ObjectMapper()
				.findAndRegisterModules()
				.configure(SerializationFeature.WRAP_ROOT_VALUE, false)
				.writer();
		
		String payloadJson = objectWriter.writeValueAsString(bootcampInput);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/bootcamps")
				.contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
	}
	
	@Test
	public void updateBootcampTest() throws Exception {
		BootcampInput bootcampInput = new BootcampInput("BC15 Part Time", 1L, 
				LocalDate.of(2022, 10, 05), LocalDate.of(2023, 4, 15), StatusBootcamp.PROXIMATURMA.toString());
		
		ObjectWriter objectWriter = new ObjectMapper()
				.findAndRegisterModules()
				.configure(SerializationFeature.WRAP_ROOT_VALUE, false)
				.writer();
		
		String payloadJson = objectWriter.writeValueAsString(bootcampInput);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/bootcamps/{idBootcamp}", 1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(payloadJson))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();	
	}
	
	@Test
	public void searchBootcampByIdTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/bootcamps/{idBootcamp}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk());		
	}
	
	@Test
	public void buscarTodosBootcampsTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/bootcamps")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void deletarBootcampById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/bootcamps/{idBootcamp}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
}
