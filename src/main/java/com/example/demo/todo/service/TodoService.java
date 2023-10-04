package com.example.demo.todo.service;

import com.example.demo.todo.entity.TodoEntity;
import com.example.demo.todo.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<TodoEntity> create(final TodoEntity entity){
        validate(entity);

        todoRepository.save(entity);
        log.info("Entity Id : {} is saved", entity.getId());

        return todoRepository.findByUserId(entity.getUserId());
    }

    public void validate(final TodoEntity entity){
        if (entity == null){
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }

        if (entity.getUserId() == null){
            log.warn("Unknown User.");
            throw new RuntimeException("Unknown User.");
        }
    }

    public List<TodoEntity> retrieveTodo(final String userId) {
        return todoRepository.findByUserId(userId);
    }

    public List<TodoEntity> updateTodo(final TodoEntity entity) {
        validate(entity);

        Optional<TodoEntity> origin = todoRepository.findById(entity.getId());

        origin.ifPresent(todo -> {
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());

            todoRepository.save(todo);
        });

        return todoRepository.findByUserId(entity.getUserId());
    }

    public List<TodoEntity> deleteTodo(final TodoEntity entity) {
        validate(entity);

        try {
            todoRepository.delete(entity);
        } catch (Exception e) {
            log.error("error deleting entity : {}", entity.getId(), e);
            throw new RuntimeException("error deleting entity : " + entity.getId());
        }

        return todoRepository.findByUserId(entity.getUserId());
    }
}
