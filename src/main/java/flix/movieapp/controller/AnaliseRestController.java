package flix.movieapp.controller;

import flix.movieapp.model.Analise;
import flix.movieapp.service.AnaliseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analises")
public class AnaliseRestController {

    @Autowired
    private AnaliseService analiseService;

    @GetMapping
    public List<Analise> getAllAnalises() {
        return analiseService.listarAnalises();
    }

    @GetMapping("/{id}")
    public Analise getAnaliseById(@PathVariable Long id) {
        return analiseService.buscarAnalisePorId(id);
    }

    @PostMapping
    public Analise createAnalise(@RequestBody Analise analise) {
        return analiseService.adicionarAnalise(analise);
    }

    @PutMapping("/{id}")
    public Analise updateAnalise(@PathVariable Long id, @RequestBody Analise analise) {
        return analiseService.atualizar(id, analise);
    }

    @DeleteMapping("/{id}")
    public void deleteAnalise(@PathVariable Long id) {
        analiseService.deletar(id);
    }
}
