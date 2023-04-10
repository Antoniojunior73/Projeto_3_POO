package br.com.projetopoo.cadastroalunos.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.com.projetopoo.cadastroalunos.controller.request.AddMateria;
import br.com.projetopoo.cadastroalunos.exception.MateriaNaoEncontradaException;
import br.com.projetopoo.cadastroalunos.model.Materia;
import br.com.projetopoo.cadastroalunos.repository.MateriaRepository;
import br.com.projetopoo.cadastroalunos.controller.request.AtualizarMateria;

@Service
public class MateriaService {
    private final MateriaRepository materiaRepository;

    public MateriaService(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    public List<Materia> listar() {
        return materiaRepository.findAll();
    }

    public Materia getMateria(Long id) {
        return materiaRepository.findById(id)
                .orElseThrow(() -> new MateriaNaoEncontradaException("A materia com o ID: " + id + " não foi encontrada"));
    }

    public Materia adicionar(AddMateria adicionarMateria) {
        var data = Instant.now();

        var materia = new Materia();
        BeanUtils.copyProperties(adicionarMateria, materia);
        materia.setDataCadastro(data);
        materia.setUltimaAtualizacao(data);
        materiaRepository.save(materia);

        return materia;
    }

    public Materia atualizar(AtualizarMateria atualizarMateria) {
        var materia = materiaRepository.findById(atualizarMateria.getId()).get();

        BeanUtils.copyProperties(atualizarMateria, materia);
        materia.setUltimaAtualizacao(Instant.now());
        materiaRepository.save(materia);
        
        return materia;
    }

    public void deletar(Long id) {
        materiaRepository.deleteById(id);
    }
}
