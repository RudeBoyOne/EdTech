package com.edtech_api.api.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.edtech_api.api.assembler.ProfessorAssembler;
import com.edtech_api.api.dtos.inputs.ProfessorInput;
import com.edtech_api.api.dtos.outputs.ProfessorOutput;
import com.edtech_api.domain.model.Carreira;
import com.edtech_api.domain.model.HabilidadeTecnica;
import com.edtech_api.domain.model.Professor;
import com.edtech_api.domain.service.CarreiraService;
import com.edtech_api.domain.service.HabilidadeTecnicaService;
import com.edtech_api.domain.service.ProfessorService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/professores")
public class ProfessorController {

	private final ProfessorService professorService;
	
	private final ProfessorAssembler professorAssembler;
	
	private final CarreiraService carreiraService;
	
	private final HabilidadeTecnicaService habilidadeTecnicaService;
	
	private final Logger logger = LoggerFactory.getLogger(ProfessorController.class);
	
	@PostMapping
	public ResponseEntity<ProfessorOutput> criar(@Valid @RequestBody ProfessorInput professor){
		Carreira carreira = carreiraService.buscarCarreiraById(professor.getCarreira());
		List<HabilidadeTecnica> habilidades = professor.getHabilidades().stream()
				.map(habilidade -> habilidadeTecnicaService.buscarHabilidadeTecnicaById(habilidade)).collect(Collectors.toList());
		Professor professorEntity = professorAssembler.toEntity(professor);
		professorEntity.setCarreira(carreira);
		professorEntity.setHabilidadesTecnicas(habilidades);
		Professor professorSalvoEntity = professorService.criarProfessor(professorEntity);
		ProfessorOutput professorOutput = professorAssembler.toOutput(professorSalvoEntity);
		logger.info("POST PROFESSOR - Realizado com sucesso!");
		return ResponseEntity.status(201).body(professorOutput);
	}
	
	@PutMapping("/{idProfessor}")
	public ResponseEntity<ProfessorOutput> atualizar(@PathVariable Long idProfessor, @Valid @RequestBody ProfessorInput professor){
		if(!professorService.existeProfessorById(idProfessor)) {
			logger.error("PUT PROFESSOR - atualizar professor - ERRO professor com o id: "+ idProfessor + " não encontrado");
			return ResponseEntity.notFound().build();
		}
		Carreira carreira = carreiraService.buscarCarreiraById(professor.getCarreira());
		List<HabilidadeTecnica> habilidades = professor.getHabilidades().stream()
				.map(habilidade -> habilidadeTecnicaService.buscarHabilidadeTecnicaById(habilidade)).collect(Collectors.toList());
		Professor professorEntity = professorAssembler.toEntity(professor);
		professorEntity.setCarreira(carreira);
		professorEntity.setHabilidadesTecnicas(habilidades);
		professorEntity.setIdProfessor(idProfessor);
		Professor professorSalvoEntity = professorService.criarProfessor(professorEntity);
		ProfessorOutput professorOutput = professorAssembler.toOutput(professorSalvoEntity);
		logger.info("PUT PROFESSOR - professor de id "+ idProfessor + " - Alterado com sucesso!");
		return ResponseEntity.ok(professorOutput);
	}
	
	@GetMapping
	public List<ProfessorOutput> listar(){
		List<Professor> professoresEntity = professorService.listarTodosProfessores();
		List<ProfessorOutput> professorOutputs = professorAssembler.toCollectionOutput(professoresEntity);
		logger.info("GET PROFESSOR - listar todos professores - Realizado com sucesso!");
		return professorOutputs;
	}
	
	@GetMapping("/{idProfessor}")
	public ResponseEntity<ProfessorOutput> buscarById(@PathVariable Long idProfessor){
		Professor professor = professorService.buscarProfessorById(idProfessor);
		ProfessorOutput professorOutput = professorAssembler.toOutput(professor);
		logger.info("GET PROFESSOR - buscar de professor by id: "+ idProfessor + " - Realizado com sucesso!");
		return ResponseEntity.ok(professorOutput);
	}
	
	@DeleteMapping("/{idProfessor}")
	public ResponseEntity<Void> deleteById(@PathVariable Long idProfessor){
		professorService.deletarProfessorById(idProfessor);
		logger.info("DELETE PROFESSOR - deleção de professor by id: "+ idProfessor + " - Realizado com sucesso!");
		return ResponseEntity.noContent().build();
	}
}
