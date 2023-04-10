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
//import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.projetopoo.cadastroalunos.controller.request.AddMateria;
import br.com.projetopoo.cadastroalunos.controller.request.AddMateriaResponse;
import br.com.projetopoo.cadastroalunos.controller.request.AtualizarMateria;
import br.com.projetopoo.cadastroalunos.exception.MateriaNaoEncontradaException;
import br.com.projetopoo.cadastroalunos.model.Materia;
import br.com.projetopoo.cadastroalunos.service.MateriaService;


@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
	private final MateriaService materiaService;
    private final ObjectMapper map = new ObjectMapper();

    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @GetMapping()
    public ResponseEntity<List<Materia>> listar() {
    	
        return new ResponseEntity<>(materiaService.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> ler(@PathVariable Long id) {
        try {
            Materia materia = materiaService.getMateria(id);
            return ResponseEntity.ok(materia);
        } catch (MateriaNaoEncontradaException e) {
            return handleAlunoNaoEncontrado(e);
        }
    }

    @ExceptionHandler(MateriaNaoEncontradaException.class)
    public ResponseEntity<Object> handleAlunoNaoEncontrado(MateriaNaoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

  
    @PostMapping()
    public ResponseEntity<AddMateriaResponse> incluir(@RequestParam String materiaData) throws IOException {

        final var addMateria = map.readValue(materiaData, AddMateria.class);

        var materia = materiaService.adicionar(addMateria);
        var materiaResponse = new AddMateriaResponse();
        
        BeanUtils.copyProperties(materia, materiaResponse);
        
        return new ResponseEntity<>(materiaResponse, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Materia> atualizar(@RequestParam String materiaData) throws IOException {
        final var atualizarMateria = map.readValue(materiaData, AtualizarMateria.class);

        var materia = materiaService.atualizar(atualizarMateria);
        
        return new ResponseEntity<>(materia, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
    	
        materiaService.deletar(id);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
}