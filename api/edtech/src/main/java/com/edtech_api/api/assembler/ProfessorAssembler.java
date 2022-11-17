package com.edtech_api.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edtech_api.api.dtos.inputs.ProfessorInput;
import com.edtech_api.api.dtos.outputs.ProfessorOutput;
import com.edtech_api.domain.model.Professor;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProfessorAssembler {

	private final ModelMapper modelMapper;
	
	public ProfessorOutput toOutput(Professor professor) {
		return modelMapper.map(professor, ProfessorOutput.class);
	}
	
	public List<ProfessorOutput> toCollectionOutput(List<Professor> professores){
		return professores.stream().map(this::toOutput).collect(Collectors.toList());
	}
	
	public Professor toEntity(ProfessorInput professorInput) {
		return modelMapper.map(professorInput, Professor.class);
	}
}
