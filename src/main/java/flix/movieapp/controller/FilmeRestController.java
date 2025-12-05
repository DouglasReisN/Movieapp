package flix.movieapp.controller;

import flix.movieapp.model.Filme;
import flix.movieapp.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/filmes")
public class FilmeRestController {

    @Autowired
    private FilmeService filmeService;

    // Listar todos os filmes
    @GetMapping
    public List<Filme> getAllFilmes() {
        return filmeService.listarFilmes();
    }

    // Buscar filme por ID
    @GetMapping("/{id}")
    public Filme getFilmeById(@PathVariable Long id) {
        return filmeService.buscarFilmePorId(id);
    }

    // Adicionar novo filme
    @PostMapping
    public Filme adicionarFilme(@RequestBody Filme filme) {
        return filmeService.adicionarFilme(filme);
    }

    // Atualizar filme
    @PutMapping("/{id}")
    public Filme updateFilme(@PathVariable Long id, @RequestBody Filme filme) {
        return filmeService.atualizar(id, filme);
    }

    // Excluir filme
    @DeleteMapping("/{id}")
    public void deleteFilme(@PathVariable Long id) {
        filmeService.deletar(id);
    }

    // Buscar filme por título
    @GetMapping("/busca")
    public ResponseEntity<Filme> buscarPorTitulo(@RequestParam String titulo) {
        Optional<Filme> filme = filmeService.buscarPorTitulo(titulo);
        return filme.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Sugerir títulos (autocomplete)
    @GetMapping("/sugestoes")
    public List<String> sugerirTitulos(@RequestParam String termo) {
        return filmeService.buscarTitulosPorTermo(termo);
    }
}
