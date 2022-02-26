package com.puc.clinicas.security.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenMalformedException extends AuthenticationException {
	
	private static final long serialVersionUID = 5355222075106109884L;

	public JwtTokenMalformedException(String msg) {
		super(msg);
	}
}
