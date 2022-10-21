package com.edtech_api.domain.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class Extensao {

	@NonNull
	private String nome;
	@NonNull
	private String especialidade;
	@NonNull
	private LocalDate dataInicio;
	@NonNull
	private LocalDate dataTermino;
}
