package com.edtech_api.domain.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@RequiredArgsConstructor
public class Bootcamp {

	@NonNull
	private String nome;
	@NonNull
	private Carreira carreira;
	@NonNull
	private LocalDate dataInicio;
	@NonNull
	private LocalDate dataTermino;
}
