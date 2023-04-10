package br.com.projetopoo.cadastroalunos.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetopoo.cadastroalunos.controller.request.AddAluno;
import br.com.projetopoo.cadastroalunos.exception.AlunoNaoEncontradoException;
import br.com.projetopoo.cadastroalunos.model.Aluno;
import br.com.projetopoo.cadastroalunos.repository.AlunoRepository;
import br.com.projetopoo.cadastroalunos.controller.request.AtualizarAluno;


@Service
public class AlunoService {
	
	private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<Aluno> listar() {
        return alunoRepository.findAll();
    }
    

    public Aluno getAluno(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new AlunoNaoEncontradoException("Aluno com esse ID não encontrado. Volte e tente novamente"));
    }
    
    
    @Transactional
    public Aluno adicionar(AddAluno adicionarAluno) {
        var data = Instant.now();

        var aluno = new Aluno();
        BeanUtils.copyProperties(adicionarAluno, aluno);
        aluno.setDataCadastro(data);
        aluno.setUltimaAtualizacao(data);
        alunoRepository.save(aluno);

        if (adicionarAluno == null) {
            throw new IllegalArgumentException("addAluno é obrigatório");
        }

        return aluno;
    }
    @Transactional
    public Aluno atualizar(AtualizarAluno atualizarAluno) {
        var aluno = alunoRepository.findById(atualizarAluno.getId()).get();

        BeanUtils.copyProperties(atualizarAluno, aluno);
        aluno.setUltimaAtualizacao(Instant.now());
        alunoRepository.save(aluno);

        if (atualizarAluno.getId() == null) {
            throw new IllegalArgumentException("Id é obrigatório");
        }
        
        return aluno;
    }
    
    @Transactional
    public void deletar(Long id) {
        alunoRepository.deleteById(id);
    }
}