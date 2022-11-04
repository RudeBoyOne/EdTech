package com.edtech_api.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusBootcamp {
	
	ABERTA("Aberta"),

    PROXIMATURMA("Proxima Turma"),

    ENCERRADA("Encerrada");
	
	private String descricao;
	

}
