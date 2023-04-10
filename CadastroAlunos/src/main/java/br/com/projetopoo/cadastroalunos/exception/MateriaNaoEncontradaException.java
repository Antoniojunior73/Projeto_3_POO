package br.com.projetopoo.cadastroalunos.exception;

public class MateriaNaoEncontradaException extends RuntimeException{

    private static final long serialVersionUID = 1L;

	public MateriaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}