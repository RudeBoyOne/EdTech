package com.edtech_api.api.dtos.inputs;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CarreiraInput {

	@NotBlank
	@Size(max = 60)
	private String nome;
	
	@NotNull
	private Integer duracao;
	
	@NotNull
	private Integer duracaoProjetoFinal;
}
