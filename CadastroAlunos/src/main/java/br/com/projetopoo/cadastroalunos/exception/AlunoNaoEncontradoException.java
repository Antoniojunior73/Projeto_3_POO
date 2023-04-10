package br.com.projetopoo.cadastroalunos.exception;

public class AlunoNaoEncontradoException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public AlunoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    
    public AlunoNaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
