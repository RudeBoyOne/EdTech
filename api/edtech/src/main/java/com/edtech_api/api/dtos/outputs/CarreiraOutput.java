package com.edtech_api.api.dtos.outputs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarreiraOutput {

	private Long id;
	private String nome;
	private Integer duracao;
	private Integer duracaoProjetoFinal;
}
