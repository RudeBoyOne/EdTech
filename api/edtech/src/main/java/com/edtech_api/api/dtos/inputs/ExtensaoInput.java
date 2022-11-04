package com.edtech_api.api.dtos.inputs;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExtensaoInput {

	@NotBlank
	@Size(max = 60)
	private String nome;
	
	@NotBlank
	@Size(max = 100)
	private String especialidade;

	@NotNull
	private LocalDate dataInicio;
	
	@NotNull
	private LocalDate dataTermino;
	
	@NotBlank
	@Size(max = 60)
	private String status;
}
