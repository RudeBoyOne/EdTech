package com.edtech_api.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusExtensao {

	ABERTA("Aberta"),

    PROXIMAEXTESAO("Proxima Turma"),

    ENCERRADA("Encerrada");
	
	private String descricao;
}
