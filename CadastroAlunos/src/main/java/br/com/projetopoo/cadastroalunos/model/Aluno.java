package br.com.projetopoo.cadastroalunos.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class Aluno {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String nome;
	private Long idade;
	private String curso;
	private String endereço;
    
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String telefone;
    
    @Lob
    private byte[] foto;
    private Instant dataCadastro;
    private Instant ultimaAtualizacao;

}