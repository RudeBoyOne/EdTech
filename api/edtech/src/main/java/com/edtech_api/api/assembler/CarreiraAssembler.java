package com.edtech_api.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edtech_api.api.dtos.inputs.CarreiraInput;
import com.edtech_api.api.dtos.outputs.CarreiraOutput;
import com.edtech_api.domain.model.Carreira;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CarreiraAssembler {

	private final ModelMapper modelMapper;
	
	
	public CarreiraOutput toOutput(Carreira carreira) {
		return modelMapper.map(carreira, CarreiraOutput.class);
	}
	
	public List<CarreiraOutput> toCollectionOutput(List<Carreira> carrerias){
		return carrerias.stream().map((carreira) -> this.toOutput(carreira)).collect(Collectors.toList());
	}
	
	public Carreira toEntity(CarreiraInput carreiraInput) {
		return modelMapper.map(carreiraInput, Carreira.class);
	}
}
