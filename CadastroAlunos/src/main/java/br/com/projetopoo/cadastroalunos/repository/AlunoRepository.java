package br.com.projetopoo.cadastroalunos.repository;

import br.com.projetopoo.cadastroalunos.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{
	
	List<Aluno> findAll();
}