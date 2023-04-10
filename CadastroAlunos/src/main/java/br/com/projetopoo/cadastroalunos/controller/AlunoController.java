package br.com.projetopoo.cadastroalunos.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.projetopoo.cadastroalunos.controller.request.AddAluno;
import br.com.projetopoo.cadastroalunos.controller.request.AddAlunoResponse;
import br.com.projetopoo.cadastroalunos.controller.request.AtualizarAluno;
import br.com.projetopoo.cadastroalunos.exception.AlunoNaoEncontradoException;
import br.com.projetopoo.cadastroalunos.model.Aluno;
import br.com.projetopoo.cadastroalunos.service.AlunoService;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private final AlunoService alunoService;
    @Autowired
    private final ObjectMapper map = new ObjectMapper();


    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }


    @GetMapping()
    public ResponseEntity<List<Aluno>> listar() {
        return new ResponseEntity<>(alunoService.listar(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> ler(@PathVariable Long id) {
        try {
            Aluno aluno = alunoService.getAluno(id);
            return ResponseEntity.ok(aluno);
        } catch (AlunoNaoEncontradoException e) {
            return handleAlunoNaoEncontrado(e);
        }
    }

    @ExceptionHandler(AlunoNaoEncontradoException.class)
    public ResponseEntity<Object> handleAlunoNaoEncontrado(AlunoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @PostMapping()
    public ResponseEntity<AddAlunoResponse> incluir(@RequestParam String alunoData,
            @RequestParam("file") final MultipartFile file) throws IOException {

        final var addAluno = map.readValue(alunoData, AddAluno.class);
        addAluno.setFoto(file.getInputStream().readAllBytes());

        var aluno = alunoService.adicionar(addAluno);
        var alunoResponse = new AddAlunoResponse();

        BeanUtils.copyProperties(aluno, alunoResponse);

        return new ResponseEntity<>(alunoResponse, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Aluno> atualizar(@RequestParam String alunoData,
            @RequestParam(value = "file", required = false) final MultipartFile file) throws IOException {
        final var atualizarAluno = map.readValue(alunoData, AtualizarAluno.class);

        if (file != null) {
            atualizarAluno.setFoto(file.getInputStream().readAllBytes());
        }

        var aluno = alunoService.atualizar(atualizarAluno);

        return new ResponseEntity<>(aluno, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {

        alunoService.deletar(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}