package flix.movieapp.repository;

import flix.movieapp.model.Filme;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
     Optional<Filme> findByTituloIgnoreCase(String titulo);
     List<Filme> findByTituloContainingIgnoreCase(String termo);

}
