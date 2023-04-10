package br.com.projetopoo.cadastroalunos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import br.com.projetopoo.cadastroalunos.exception.MatriculaNaoEncontradoException;
import br.com.projetopoo.cadastroalunos.model.Matricula;
import br.com.projetopoo.cadastroalunos.service.MatriculaService;

@RestController
@RequestMapping("/matricula")
public class MatriculaController {

    @Autowired
    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;

    }

    @GetMapping()
    public ResponseEntity<List<Matricula>> listar() {

        return new ResponseEntity<>(matriculaService.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> ler(@PathVariable Long id) {
        try {
            Matricula matricula = matriculaService.getMatricula(id);
            return ResponseEntity.ok(matricula);
        } catch (MatriculaNaoEncontradoException e) {
            return handleMatriculaNaoEncontrado(e);
        }
    }

    @ExceptionHandler(MatriculaNaoEncontradoException.class)
    public ResponseEntity<Object> handleMatriculaNaoEncontrado(MatriculaNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @PostMapping("/{id}/IRA")
    public ResponseEntity<Object> calcularMedia(@PathVariable Long id) {
        try {
            double ira = matriculaService.calcularMedia(id);
            return ResponseEntity.ok(ira);
        } catch (MatriculaNaoEncontradoException e) {
            return handleMatriculaNaoEncontrado(e);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {

        matriculaService.deletar(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
