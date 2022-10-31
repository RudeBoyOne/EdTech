package com.edtech_api.domain.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RecursoNaoEncontrado extends EdtechException{

	private static final long serialVersionUID = 1L;

	public RecursoNaoEncontrado(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return "RecursoNaoEncontrado =" + super.toString();
	}
	
	
}
