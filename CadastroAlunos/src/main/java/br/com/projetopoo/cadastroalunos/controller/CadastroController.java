package br.com.projetopoo.cadastroalunos.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetopoo.cadastroalunos.controller.request.MateriaAddNota;
import br.com.projetopoo.cadastroalunos.model.*;
import br.com.projetopoo.cadastroalunos.repository.*;

@RestController
public class CadastroController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @PostMapping("/cadastro")
    public ResponseEntity<?> vincularMatricula(@RequestParam Long alunoId, @RequestParam Long materiaId,
            @RequestBody MateriaAddNota materiaAddNota) {

        Optional<Aluno> alunoOptional = alunoRepository.findById(alunoId);
        Optional<Materia> materiaOptional = materiaRepository.findById(materiaId);

        if (alunoOptional.isPresent() && materiaOptional.isPresent()) {

            Aluno aluno = alunoOptional.get();
            Materia materia = materiaOptional.get();
            Double[] nota = materiaAddNota.getNotas();

            Optional<Matricula> matriculaOptional = matriculaRepository.findByAlunoId(alunoId);

            if (!matriculaOptional.isPresent()) {
                Matricula matricula = new Matricula(aluno, materia);
                matricula.addMateria(materia);
                materia.setNota1(nota[0]);
                materia.setNota2(nota[1]);
                materia.setNota3(nota[2]);
                matriculaRepository.save(matricula);
            } else {
                Matricula matricula = matriculaOptional.get();
                Optional<Materia> existingMateriaOptional = matricula.getMaterias().stream()
                        .filter(m -> m.getId().equals(materiaId)).findFirst();
                if (existingMateriaOptional.isPresent()) {
                    Materia existingMateria = existingMateriaOptional.get();
                    existingMateria.setNota1(nota[0]);
                    existingMateria.setNota2(nota[1]);
                    existingMateria.setNota3(nota[2]);
                } else {
                    matricula.addMateria(materia);
                    materia.setNota1(nota[0]);
                    materia.setNota2(nota[1]);
                    materia.setNota3(nota[2]);
                }
                matriculaRepository.save(matricula);
            }

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}