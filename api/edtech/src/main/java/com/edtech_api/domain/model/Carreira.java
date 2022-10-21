package com.edtech_api.domain.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@RequiredArgsConstructor
public class Carreira {

	@NonNull
	private String nome;
	@NonNull
	private Integer duracao;
	@NonNull
	private Integer duracaoProjetoFinal;
}
