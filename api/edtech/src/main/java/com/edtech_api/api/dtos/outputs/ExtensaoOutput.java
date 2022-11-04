package com.edtech_api.api.dtos.outputs;

import java.time.LocalDate;

import com.edtech_api.domain.model.enums.StatusExtensao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExtensaoOutput {

	private Long id;
	private String nome;
	private String especialidade;
	private LocalDate dataInicio;
	private LocalDate dataTermino;
	private StatusExtensao status;
}
