package com.edtech_api.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edtech_api.api.dtos.inputs.BootcampInput;
import com.edtech_api.api.dtos.outputs.BootcampOutput;
import com.edtech_api.domain.model.Bootcamp;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BootcampAssembler {
	
	private final ModelMapper modelMapper;
	
	public BootcampOutput toOutput(Bootcamp bootcamp) {
		return modelMapper.map(bootcamp, BootcampOutput.class);
	}
	
	public List<BootcampOutput> toCollectionOutput(List<Bootcamp> bootcamps){
		return bootcamps.stream().map(this::toOutput).collect(Collectors.toList());
	}
	
	public Bootcamp toEntity(BootcampInput bootcampInput) {
		return modelMapper.map(bootcampInput, Bootcamp.class);
	}
 
}
