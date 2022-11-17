 package com.edtech_api.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edtech_api.api.assembler.BootcampAssembler;
import com.edtech_api.api.dtos.inputs.BootcampInput;
import com.edtech_api.api.dtos.outputs.BootcampOutput;
import com.edtech_api.domain.model.Bootcamp;
import com.edtech_api.domain.model.Carreira;
import com.edtech_api.domain.service.BootcampService;
import com.edtech_api.domain.service.CarreiraService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/bootcamps")
public class BootcampController {

	private final BootcampService bootcampService;
	
	private final CarreiraService carreiraService;
	
	private final BootcampAssembler bootcampAssembler;
	
	private final Logger logger = LoggerFactory.getLogger(BootcampController.class);
	
	@PostMapping
	public ResponseEntity<BootcampOutput> criar(@Valid @RequestBody BootcampInput bootcamp){
		Carreira carreira = carreiraService.buscarCarreiraById(bootcamp.getCarreira());
		Bootcamp bootcampEntity = bootcampAssembler.toEntity(bootcamp);
		bootcampEntity.setCarreira(carreira);
		Bootcamp bootcampSalvoEntity = bootcampService.criarBootcamp(bootcampEntity);
		BootcampOutput bootcampOutput = bootcampAssembler.toOutput(bootcampSalvoEntity);
		logger.info("POST BOOTCAMP - Realizado com sucesso!");
		return ResponseEntity.status(201).body(bootcampOutput);
	}
	
	@PutMapping("/{idBootcamp}")
	public ResponseEntity<BootcampOutput> atualizar(@PathVariable Long idBootcamp, @Valid @RequestBody BootcampInput bootcamp){
		if(!bootcampService.existeBootcampById(idBootcamp)) {
			logger.error("PUT BOOTCAMP - atualizar bootcamp - ERRO bootcamp com o id: "+ idBootcamp + " não encontrado");
			return ResponseEntity.notFound().build();
		}
		Carreira carreira = carreiraService.buscarCarreiraById(bootcamp.getCarreira());
		Bootcamp bootcampEntity = bootcampAssembler.toEntity(bootcamp);
		bootcampEntity.setIdBootcamp(idBootcamp);
		bootcampEntity.setCarreira(carreira);
		Bootcamp bootcampSalvoEntity = bootcampService.criarBootcamp(bootcampEntity);
		BootcampOutput bootcampOutput = bootcampAssembler.toOutput(bootcampSalvoEntity);		
		logger.info("PUT BOOTCAMP - bootcamp de id "+ idBootcamp + " - Alterado com sucesso!");
		return ResponseEntity.ok(bootcampOutput);
	}
	
	@GetMapping
	public List<BootcampOutput> listar(){
		List<Bootcamp> bootcampsEntity = bootcampService.listarTodosBootcamps();
		List<BootcampOutput> bootcampOutputs = bootcampAssembler.toCollectionOutput(bootcampsEntity);
		logger.info("GET BOOTCAMP - listar todos bootcamps - Realizado com sucesso!");
		return bootcampOutputs;
	}
	
	@GetMapping("/{idBootcamp}")
	public ResponseEntity<BootcampOutput> buscarById(@PathVariable Long idBootcamp){
		Bootcamp bootcampBuscado = bootcampService.buscarBootcampById(idBootcamp);
		BootcampOutput bootcampOutput = bootcampAssembler.toOutput(bootcampBuscado);
		logger.info("GET BOOTCAMP - buscar de bootcamp by id: "+ idBootcamp + " - Realizado com sucesso!");
		return ResponseEntity.ok(bootcampOutput);
	}
	
	@DeleteMapping("/{idBootcamp}")
	public ResponseEntity<Void> deletarById(@PathVariable Long idBootcamp){
		bootcampService.deletarBootcampById(idBootcamp);
		logger.info("DELETE BOOTCAMP - deleção de bootcamp by id: "+ idBootcamp + " - Realizado com sucesso!");
		return ResponseEntity.noContent().build();
	}
}
