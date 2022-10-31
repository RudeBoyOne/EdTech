package com.edtech_api.domain.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EdtechException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	

	public EdtechException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return "EdtechException =" + super.toString();
	}
	
}
