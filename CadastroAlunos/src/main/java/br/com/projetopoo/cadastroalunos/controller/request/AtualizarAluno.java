package br.com.projetopoo.cadastroalunos.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AtualizarAluno {

	   private Long id;
	   private String nome;
	   private Long idade;
	   private String curso;
	   private String endereço;
	   private String telefone;
	   private String email;
	   private byte[] foto;
}
