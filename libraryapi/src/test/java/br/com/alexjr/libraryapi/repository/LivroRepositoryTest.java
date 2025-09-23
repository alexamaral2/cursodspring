package br.com.alexjr.libraryapi.repository;

import br.com.alexjr.libraryapi.model.Autor;
import br.com.alexjr.libraryapi.model.GeneroLivro;
import br.com.alexjr.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
public class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarAutorELivro() {
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Terceiro Livro");
        livro.setDataPublicacao(LocalDate.of(1980,1,2));

        Autor autor = new Autor();
        autor.setNome("José");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1995, 3, 10));

        Autor autorSalvo = autorRepository.save(autor);
        livro.setAutor(autorSalvo);

        livroRepository.save(livro);
    }

    @Test
    void salvarCascadeTest() {
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Outro Livro");
        livro.setDataPublicacao(LocalDate.of(1980,1,2));

        Autor autor = new Autor();
        autor.setNome("João");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1995, 3, 10));

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Outro Livro");
        livro.setDataPublicacao(LocalDate.of(1980,1,2));

        Autor autor = autorRepository
                .findById(UUID.fromString("e11039b7-edef-4c91-83af-f5d47cf111e9"))
                .orElse(null);
        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void atualizarAutorDoLivro() {
        UUID id = UUID.fromString("3a51d3a9-3946-4107-9a53-ee1b36c727ee");
        var livroParaAtualizar = livroRepository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("e11039b7-edef-4c91-83af-f5d47cf111e9");
        Autor autor = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(autor);

        livroRepository.save(livroParaAtualizar);
    }

    @Test
    void deletar() {
        UUID id = UUID.fromString("3a51d3a9-3946-4107-9a53-ee1b36c727ee");
        livroRepository.deleteById(id);
    }

    @Test
    void deletarCascade() {
        UUID id = UUID.fromString("3a51d3a9-3946-4107-9a53-ee1b36c727ee");
        livroRepository.deleteById(id);
    }
}
