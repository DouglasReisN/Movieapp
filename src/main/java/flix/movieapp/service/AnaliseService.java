package flix.movieapp.service;

import flix.movieapp.model.Analise;
import flix.movieapp.repository.AnaliseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnaliseService {

    @Autowired
    private AnaliseRepository analiseRepository;

    public Analise adicionarAnalise(Analise analise) {
        return analiseRepository.save(analise);
    }

    public List<Analise> listarAnalises() {
        return analiseRepository.findAll();
    }

    public Analise buscarAnalisePorId(Long id) {
        return analiseRepository.findById(id).orElse(null);
    }

    public Analise atualizar(Long id, Analise analiseAtualizada) {
        return analiseRepository.findById(id)
                .map(analise -> {
                    analise.setAnalise(analiseAtualizada.getAnalise());
                    analise.setNota(analiseAtualizada.getNota());
                    analise.setFilme(analiseAtualizada.getFilme());
                    return analiseRepository.save(analise);
                })
                .orElse(null);
    }

    public void deletar(Long id) {
        analiseRepository.deleteById(id);
    }
}
