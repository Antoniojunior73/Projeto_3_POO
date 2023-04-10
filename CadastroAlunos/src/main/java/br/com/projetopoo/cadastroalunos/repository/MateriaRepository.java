package br.com.projetopoo.cadastroalunos.repository;

import br.com.projetopoo.cadastroalunos.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MateriaRepository extends JpaRepository<Materia, Long>{
    List<Materia> findAll();
}