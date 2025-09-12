package io.github.alexamaral2.arquiteturaspring.todos;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private TodoRepository repository;
    private TodoValidator validator;
    private MailSender mailSender;

    public TodoService(TodoRepository repository, TodoValidator validator, MailSender mailSender) {
        this.repository = repository;
        this.validator = validator;
        this.mailSender = mailSender;
    }

    public TodoEntity salvar(TodoEntity todo) {
        validator.validar(todo);
        return repository.save(todo);
    }

    public void atualizarStatus(TodoEntity todo) {
        repository.save(todo);
        String concluido = todo.getConcluido() == Boolean.TRUE ? "Concluido" : "Não Concluido";
        mailSender.enviar("Todo de código " + todo.getDescricao() + " foi " + concluido);
    }

    public TodoEntity buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
