package flix.movieapp.service;

import flix.movieapp.model.Filme;
import flix.movieapp.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    public Filme adicionarFilme(Filme filme) {
        return filmeRepository.save(filme);
    }

    public List<Filme> listarFilmes() {
        return filmeRepository.findAll();
    }

    public Filme buscarFilmePorId(Long id) {
        return filmeRepository.findById(id).orElse(null);
    }

    public Optional<Filme> buscarPorTitulo(String titulo) {
        return filmeRepository.findByTituloIgnoreCase(titulo);
    }
    
    public List<String> buscarTitulosPorTermo(String termo) {
    return filmeRepository.findByTituloContainingIgnoreCase(termo)
                          .stream()
                          .map(Filme::getTitulo)
                          .collect(Collectors.toList());
}


    public Filme atualizar(Long id, Filme filmeAtualizado) {
        return filmeRepository.findById(id)
                .map(filme -> {
                    filme.setTitulo(filmeAtualizado.getTitulo());
                    filme.setGenero(filmeAtualizado.getGenero());
                    filme.setSinopse(filmeAtualizado.getSinopse());
                    filme.setAnoLancamento(filmeAtualizado.getAnoLancamento());
                    return filmeRepository.save(filme);
                })
                .orElse(null);
    }

    public void deletar(Long id) {
        filmeRepository.deleteById(id);
    }
}
