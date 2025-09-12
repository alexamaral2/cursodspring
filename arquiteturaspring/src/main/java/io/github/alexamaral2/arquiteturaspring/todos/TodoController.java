package io.github.alexamaral2.arquiteturaspring.todos;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("todos")
public class TodoController {

    private TodoService service;

    public TodoController(TodoService todoService) {
        this.service = todoService;
    }

    @PostMapping
    public TodoEntity salvar(@RequestBody TodoEntity todos){
        try {
            return service.salvar(todos);
        } catch(IllegalArgumentException e) {
            var mensagemErro = e.getMessage();
            throw new ResponseStatusException(HttpStatus.CONFLICT, mensagemErro);
        }
    }

    @PutMapping("{id}")
    public void atualizarStatus(
            @PathVariable("id") Integer id, @RequestBody TodoEntity todo){
        todo.setId(id);
        service.salvar(todo);
    }

    @GetMapping("{id}")
    public TodoEntity buscar(
            @PathVariable("id") Integer id) {
        return service.buscarPorId(id);
    }
}
