package br.com.projetopoo.cadastroalunos.controller.request;

import lombok.Data;

import java.time.Instant;

@Data
public class AddMateriaResponse {

    private Long id;
    private long codigo;
    private String nome;
    private long cargaHoraria;
    private Instant dataCadastro;
    private Instant ultimaAtualizacao;
}