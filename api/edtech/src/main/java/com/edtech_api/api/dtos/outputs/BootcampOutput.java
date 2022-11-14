package com.edtech_api.api.dtos.outputs;

import java.time.LocalDate;

import com.edtech_api.domain.model.enums.StatusBootcamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BootcampOutput {

	private Long id;
	private String nome;
	private CarreiraOutput carreira;
	private LocalDate dataInicio;
	private LocalDate dataTermino;
	private StatusBootcamp status;
}
