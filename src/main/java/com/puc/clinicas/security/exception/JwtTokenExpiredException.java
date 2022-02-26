package com.puc.clinicas.security.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenExpiredException extends AuthenticationException {

	private static final long serialVersionUID = 3733440250677051478L;

	public JwtTokenExpiredException() {
		super("Token expired.");
	}
}
