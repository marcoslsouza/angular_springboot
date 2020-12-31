package com.github.marcoslsouza.vendas.exception;

public class UsuarioCadastradoException extends RuntimeException {
	
	private static final long serialVersionUID = 6201320597332981072L;

	public UsuarioCadastradoException(String login) {
		super("Usuário já cadastrado para o login " + login);
	}
}
