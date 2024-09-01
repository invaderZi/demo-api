package com.zetalabs.mood.app.exception.usuario;

public class EmailJaExistenteException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailJaExistenteException(String email) {
        super("O e-mail já está em uso: " + email);
    }
}