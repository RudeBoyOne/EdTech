package com.edtech_api.api.dtos.inputs;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HabilidadeTecnicaInput {

	@NotBlank
	@Size(max = 60)
	private String nome;
}
