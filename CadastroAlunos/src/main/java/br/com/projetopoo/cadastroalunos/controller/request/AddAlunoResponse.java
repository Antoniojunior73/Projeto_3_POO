package br.com.projetopoo.cadastroalunos.controller.request;

import lombok.Data;

import java.time.Instant;

@Data
public class AddAlunoResponse {
	
	private Long id;
	private String nome;
	private Long idade;
	private String curso;
	private String endereço;
	private String telefone;
	private String email;
    private Instant dataCadastro;
    private Instant ultimaAtualizacao;
}
