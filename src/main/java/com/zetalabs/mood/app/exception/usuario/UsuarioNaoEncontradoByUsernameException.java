package com.zetalabs.mood.app.exception.usuario;

public class UsuarioNaoEncontradoByUsernameException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoByUsernameException(String username) {
        super("Usuário não encontrado com o username: " + username);
    }
}