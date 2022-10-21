package com.edtech_api.domain.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class HabilidadeTecnica {

	@NonNull
	private String nome;
}
