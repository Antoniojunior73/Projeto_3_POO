package br.com.projetopoo.cadastroalunos.repository;

import br.com.projetopoo.cadastroalunos.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface  MatriculaRepository extends JpaRepository<Matricula, Long>{

	Optional<Matricula> findByAlunoId(Long alunoId);
	List<Matricula> findAll();
}