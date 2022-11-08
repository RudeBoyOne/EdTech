package com.edtech_api.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edtech_api.api.dtos.inputs.HabilidadeTecnicaInput;
import com.edtech_api.api.dtos.outputs.HabilidadeTecnicaOutput;
import com.edtech_api.domain.model.HabilidadeTecnica;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class HabilidadeTecnicaAssembler {

	private final ModelMapper modelMapper;

	public HabilidadeTecnicaOutput toOutput(HabilidadeTecnica habilidadeTecnica) {
		return modelMapper.map(habilidadeTecnica, HabilidadeTecnicaOutput.class);
	}

	public List<HabilidadeTecnicaOutput> toCollectionOutput(List<HabilidadeTecnica> habilidadesTecnicas) {
		return habilidadesTecnicas.stream().map(this::toOutput).collect(Collectors.toList());
	}

	public HabilidadeTecnica toEntity(HabilidadeTecnicaInput habilidadeTecnicaInput) {
		return modelMapper.map(habilidadeTecnicaInput, HabilidadeTecnica.class);
	}
}
