package br.com.projetopoo.cadastroalunos.exception;

public class MatriculaNaoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

	public MatriculaNaoEncontradoException(String mensagem) {
        super(mensagem);
    }   
}
