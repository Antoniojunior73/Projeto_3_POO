package br.com.projetopoo.cadastroalunos.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Aluno aluno;
    
    @ManyToMany
    private List<Materia> materias = new ArrayList<>();


    public void addMateria(Materia materia) {
        if (!materias.contains(materia)) {
            materias.add(materia);
        }
    }

    
    public Matricula() {
        // construtor padrão vazio
    }

    public Matricula(Aluno aluno, Materia materias) {
        this.aluno = aluno;
        this.materias.add(materias); 
    }

    public Matricula(Aluno aluno) {
        this.aluno = aluno;
    }

}
