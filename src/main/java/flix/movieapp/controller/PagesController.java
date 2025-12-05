package flix.movieapp.controller;

import flix.movieapp.model.Filme;
import flix.movieapp.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PagesController {

    @Autowired
    private FilmeService filmeService;

    // -------------------------------
    // Página inicial
    // -------------------------------
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // -------------------------------
    // Página de filmes
    // -------------------------------
    @GetMapping("/filmes")
    public String filmes(Model model) {
        model.addAttribute("filmes", filmeService.listarFilmes());
        return "filmes";
    }

    // -------------------------------
    // Página de análises (manter para compatibilidade)
    // -------------------------------
    @GetMapping("/analises")
    public String analises() {
        return "analises";
    }

    // -------------------------------
    // Página de adicionar filme (via JavaScript)
    // -------------------------------
    @GetMapping("/add-filme")
    public String addFilme() {
        return "add-filme";
    }

    // -------------------------------
    // Página de adicionar análise (separada)
    // -------------------------------
    @GetMapping("/add-analise")
    public String addAnalise() {
        return "add-analise";
    }

    // -------------------------------
    // Página de edição de filme
    // -------------------------------
    @GetMapping("/edit-filme")
    public String editFilme() {
        return "edit-filme";
    }

    // -------------------------------
    // Página de edição de análise
    // -------------------------------
    @GetMapping("/edit-analise")
    public String editAnalise() {
        return "edit-analise";
    }

    // -------------------------------
    // Página de detalhes do filme (com query param)
    // -------------------------------
    @GetMapping("/detalhes-filme")
    public String detalhesFilme(@RequestParam Long id, Model model) {
        Filme filme = filmeService.buscarFilmePorId(id);

        if (filme != null) {
            model.addAttribute("filme", filme);
            return "detalhes-filme";
        } else {
            return "redirect:/filmes"; // Redireciona caso não encontre
        }
    }

    // -------------------------------
    //  /filmes/{id}
    // -------------------------------
    @GetMapping("/filmes/{id}")
    public String detalhesFilmePorId(@PathVariable Long id, Model model) {
        Filme filme = filmeService.buscarFilmePorId(id);
        if (filme != null) {
            model.addAttribute("filme", filme);
            return "detalhes-filme";
        } else {
            return "redirect:/filmes"; // Redireciona caso não encontre
        }
    }

    // -------------------------------
    // Endpoint para deletar filme (via botão na tabela)
    // -------------------------------
    @GetMapping("/deletar-filme")
    public String deletarFilme(@RequestParam Long id) {
        filmeService.deletar(id);
        return "redirect:/filmes";
    }
}
