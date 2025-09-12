package io.github.alexamaral2.arquiteturaspring.todos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity,Integer> {
}
