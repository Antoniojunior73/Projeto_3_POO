package br.com.projetopoo.cadastroalunos.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class AddMateria {
    private long codigo;
    private String nome;
    private long cargaHoraria;
}