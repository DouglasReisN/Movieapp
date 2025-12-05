package flix.movieapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Analise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "filme_id")  // Nome da coluna no banco de dados
    @JsonIgnore
    private Filme filme;

    private String autor;

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    @Column(length = 2000) // Mantém o nome "analise" para compatibilidade com páginas antigas
    private String analise;
    private int nota;

    public Analise() {
        // Construtor padrão
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public String getAnalise() {
        return analise;
    }

    public void setAnalise(String analise) {
        this.analise = analise;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    // --- 🔁 Compatibilidade com o front que usa "comentario" ---
    /**
     * Ao serializar para JSON, também expõe "comentario" com o mesmo conteúdo
     * de "analise".Ex.: { "id":1, "autor":"...", "analise":"...",
     * "comentario":"..." }
     *
     * @return
     */
    @JsonProperty("comentario")
    public String getComentario() {
        return this.analise;
    }

    /**
     * Ao desserializar JSON, aceita "comentario" e salva em "analise".Ou seja,
     * POST { "autor": "...", "comentario": "..." } funciona normalmente.
     *
     * @param comentario
     */
    @JsonProperty("comentario")
    public void setComentario(String comentario) {
        this.analise = comentario;
    }
}
