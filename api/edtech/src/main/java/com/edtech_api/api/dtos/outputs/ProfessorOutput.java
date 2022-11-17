package com.edtech_api.api.dtos.outputs;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessorOutput {

	private Long id;
	private String nome;
	private String email;
	private List<HabilidadeTecnicaOutput> habilidades;
	private CarreiraOutput carreira;
}
