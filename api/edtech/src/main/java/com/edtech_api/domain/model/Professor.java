package com.edtech_api.domain.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class Professor {

	@NonNull
	private String nome;
	@NonNull
	private String email;
	@NonNull
	private HabilidadeTecnica habilidadeTecnica;
	@NonNull
	private Carreira carreira;
}
