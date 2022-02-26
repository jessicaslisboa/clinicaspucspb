package com.puc.clinicas.security.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenMissingException extends AuthenticationException{

	private static final long serialVersionUID = 4842133440255315281L;

	public JwtTokenMissingException(String msg) {
        super(msg);
    }
    
}
