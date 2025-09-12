package io.github.alexamaral2.arquiteturaspring.todos;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoEntity salvar(TodoEntity todoEntity){
        return todoRepository.save(todoEntity);
    }
}
