package br.com.projetopoo.cadastroalunos.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.time.Instant;
//import java.util.ArrayList;

@Data
@Entity
@Getter
@Setter
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
	private Long cargaHoraria;
	@Column(unique = true)
    private Long codigo;
    
    @Lob
    private Instant dataCadastro;
    private Instant ultimaAtualizacao;
    
    
    private Double nota1, nota2,nota3;

    public void setNota1(Double nota1) {
        this.nota1 = nota1;
    }


    public void setNota2(Double nota2) {
        this.nota2 = nota2;
    }


    public void setNota3(Double nota3) {
        this.nota3 = nota3;
    }

    
}