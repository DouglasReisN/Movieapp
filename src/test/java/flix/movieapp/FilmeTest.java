package flix.movieapp;

import flix.movieapp.model.Filme;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

/**
 * Testes unitários simples
 */
public class FilmeTest {

    // -------------------------------
    // 1) Testa o construtor padrão (sem argumentos)
    // -------------------------------
    @Test
    public void testConstrutorPadrao_deveInicializarCamposNulosOuZero() {
        // Cria um Filme usando o construtor sem argumentos
        Filme filme = new Filme();

        // Depois de criado, esperamos que id, titulo, sinopse e genero sejam null
        // e anoLancamento seja 0 (valor padrão para int).
        assertNull(filme.getId(), "Id deve ser null no construtor padrão");
        assertNull(filme.getTitulo(), "Título deve ser null no construtor padrão");
        assertNull(filme.getSinopse(), "Sinopse deve ser null no construtor padrão");
        assertNull(filme.getGenero(), "Gênero deve ser null no construtor padrão");
        assertEquals(0, filme.getAnoLancamento(), "Ano de lançamento deve ser 0 no construtor padrão");
    }

    // -------------------------------
    // 2) Testa o construtor parametrizado e os getters
    // -------------------------------
    @Test
    public void testConstrutorParametrizado_eGetters() {

        // Usado 1L aqui para simular um id que viria do banco.
        Filme filme = new Filme(1L, "Matrix", "Sinopse Matrix", "Sci-Fi", 1999);

        // Verificamos se os getters retornam exatamente o que passamos ao construtor.
        assertEquals(1L, filme.getId(), "Id deve ser 1L");
        assertEquals("Matrix", filme.getTitulo(), "Título deve ser 'Matrix'");
        assertEquals("Sinopse Matrix", filme.getSinopse(), "Sinopse deve bater");
        assertEquals("Sci-Fi", filme.getGenero(), "Gênero deve ser 'Sci-Fi'");
        assertEquals(1999, filme.getAnoLancamento(), "Ano de lançamento deve ser 1999");
    }

    // -------------------------------
    // 3) Testa os setters (alteração de valores)
    // -------------------------------
    @Test
    public void testSetters_deveAtualizarCampos() {
        Filme filme = new Filme(); // começa vazio

        // Usando setters para definir valores
        filme.setId(42L);
        filme.setTitulo("O Poderoso Chefão");
        filme.setSinopse("Família e crime");
        filme.setGenero("Drama/Crime");
        filme.setAnoLancamento(1972);

        // Agora os getters devem retornar os novos valores
        assertEquals(42L, filme.getId(), "Id atualizado deve ser 42L");
        assertEquals("O Poderoso Chefão", filme.getTitulo(), "Título atualizado deve bater");
        assertEquals("Família e crime", filme.getSinopse(), "Sinopse atualizada deve bater");
        assertEquals("Drama/Crime", filme.getGenero(), "Gênero atualizado deve bater");
        assertEquals(1972, filme.getAnoLancamento(), "Ano atualizado deve ser 1972");
    }

    // -------------------------------
    // 4) Testa comportamento com strings vazias — garante que setters aceitam vazios
    // -------------------------------
    @Test
    public void testSetTituloParaStringVazia_devePermitirEVirarVazio() {
        Filme filme = new Filme();
        filme.setTitulo(""); // define título vazio

        // Se sua classe não valida, o getter deve retornar exatamente a string vazia
        assertEquals("", filme.getTitulo(), "Se definir título para string vazia, getter retorna string vazia");
    }

    // -------------------------------
    // 5) Teste simples para garantir que dois objetos independentes não compartilham estado
    // -------------------------------
    @Test
    public void testObjetosIndependentes_naoCompartilhamEstado() {
        Filme f1 = new Filme(1L, "A", "S1", "G1", 2000);
        Filme f2 = new Filme(2L, "B", "S2", "G2", 2010);

        // Alterar f1 não deve alterar f2
        f1.setTitulo("A-modificado");
        assertEquals("A-modificado", f1.getTitulo(), "f1 teve o título modificado");
        assertEquals("B", f2.getTitulo(), "f2 deve manter o título original");
    }
}
