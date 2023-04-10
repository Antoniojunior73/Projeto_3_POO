package br.com.projetopoo.cadastroalunos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetopoo.cadastroalunos.exception.MatriculaNaoEncontradoException;
import br.com.projetopoo.cadastroalunos.model.Matricula;
import br.com.projetopoo.cadastroalunos.repository.MatriculaRepository;
import br.com.projetopoo.cadastroalunos.model.Materia;

@Service
public class MatriculaService {

    private Materia materia;

    private List<Materia> materias = new ArrayList<>();

    private final MatriculaRepository matriculaRepository;

    public MatriculaService(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }

    public List<Matricula> listar() {
        return matriculaRepository.findAll();
    }

    public Matricula getMatricula(Long id) {

        return matriculaRepository.findById(id)
                .orElseThrow(() -> new MatriculaNaoEncontradoException("Matrícula não encontrada!"));
    }

    public Double calcularMedia(Long id) {
        Double media = 0.0;
        Double ira;
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new MatriculaNaoEncontradoException("Matrícula não encontrada!"));
        materias = matricula.getMaterias();
        for (int i=0; i < materias.size(); i++){
            materia = materias.get(i);
            media += (materia.getNota1() * 4 + materia.getNota2() * 5 + materia.getNota3() * 6) / 15;
        }
        ira = media/materias.size();
        return ira;
    }
    
    @Transactional
    public void deletar(Long id) {
        matriculaRepository.deleteById(id);
    }

}
