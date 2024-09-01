package com.zetalabs.mood.app.exception.usuario;

public class UsernameJaExistenteException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsernameJaExistenteException(String username) {
        super("O username já está em uso: " + username);
    }
}