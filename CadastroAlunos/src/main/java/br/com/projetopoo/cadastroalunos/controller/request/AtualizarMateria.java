package br.com.projetopoo.cadastroalunos.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class AtualizarMateria {
    private Long id;
    private Long codigo;
    private Long cargaHoraria;
    private String nome;
    
}
