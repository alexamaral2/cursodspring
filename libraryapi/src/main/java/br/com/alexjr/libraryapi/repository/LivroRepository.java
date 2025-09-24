package br.com.alexjr.libraryapi.repository;

import br.com.alexjr.libraryapi.model.Autor;
import br.com.alexjr.libraryapi.model.GeneroLivro;
import br.com.alexjr.libraryapi.model.Livro;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @see LivroRepositoryTest
 */
public interface LivroRepository extends JpaRepository<Livro, UUID> {

    // Query Method
    List<Livro> findByAutor(Autor autor);

    List<Livro> findByTitulo(String titulo);

    List<Livro> findByIsbn(String isbn);

    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    List<Livro> findByTituloOrIsbnOrderByTitulo(String titulo, String isbn);

    List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);

    @Query(" select l from Livro as l order by l.titulo, l.preco ")
    List<Livro> listarTodosOrdenadoPorTituloAndPreco();

    @Query("select a from Livro l join l.autor a ")
    List<Autor> listarAutoresDosLivros();

    @Query("select distinct l.titulo from Livro l")
    List<String> listarNomesDiferentesLivros();

    @Query("""
            select l.genero
            from Livro l
            join l.autor a
            where a.nacionalidade = 'Brasileira'
            order by l.genero
    """)
    List<String> listarGenerosAutoresBrasileiros();

    @Query("select l from Livro l where l.genero = :genero order by :paramOrdenacao ")
    List<Livro> findByGenero(
            @Param("genero") GeneroLivro generoLivro,
            @Param("paramOrdenacao") String nomePropriedade
    );

    @Query("select l from Livro l where l.genero = ?2 order by ?1 ")
    List<Livro> findByGeneroPositionalParameters(String nomePropriedade, GeneroLivro generoLivro);

    @Modifying
    @Transactional
    @Query(" delete from Livro where genero = ?1 ")
    void deleteByGenero(GeneroLivro genero);

    @Modifying
    @Transactional
    @Query(" update Livro set dataPublicacao = ?1 ")
    void updateDataPublicacao(LocalDate novaData);

}
