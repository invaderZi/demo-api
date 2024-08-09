package com.example.demo.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException(Long id) {
        super("Usuário não encontrado com o id: " + id);
    }
}