package br.com.alexjr.libraryapi;

import br.com.alexjr.libraryapi.model.Autor;
import br.com.alexjr.libraryapi.repository.AutorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
        var context = SpringApplication.run(Application.class, args);
        AutorRepository autorRepository = context.getBean(AutorRepository.class);

        exemploSalvarRegistro(autorRepository);
	}

    public static void exemploSalvarRegistro(AutorRepository autorRepository) {
        Autor autor = new Autor();
        autor.setNome("Alex Junior");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1950, 1, 31));

        var autorSalvo = autorRepository.save(autor);
        System.out.println("Autor salvo com sucesso: " + autorSalvo);
    }
}
