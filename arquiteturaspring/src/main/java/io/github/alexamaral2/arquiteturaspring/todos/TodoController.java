package io.github.alexamaral2.arquiteturaspring.todos;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("todos")
public class TodoController {

    private TodoService service;

    public TodoController(TodoService todoService) {
        this.service = todoService;
    }

    @PostMapping
    public TodoEntity salvar(@RequestBody TodoEntity todos){
        return service.salvar(todos);
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
