package com.edtech_api.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edtech_api.api.dtos.inputs.ExtensaoInput;
import com.edtech_api.api.dtos.outputs.ExtensaoOutput;
import com.edtech_api.domain.model.Extensao;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ExtensaoAssembler {

	private final ModelMapper modelMapper;
	
	public ExtensaoOutput toOutput(Extensao extensao) {
		return modelMapper.map(extensao, ExtensaoOutput.class);
	}
	
	public List<ExtensaoOutput> toCollectionOutput(List<Extensao> extensoes){
		return extensoes.stream().map(this::toOutput).collect(Collectors.toList());
	}
	
	public Extensao toEntity(ExtensaoInput extensaoInput) {
		return modelMapper.map(extensaoInput, Extensao.class);
	}
}
