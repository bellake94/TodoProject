package com.example.demo.todo.controller;

import com.example.demo.todo.dto.ResponseDTO;
import com.example.demo.todo.dto.TodoDTO;
import com.example.demo.todo.entity.TodoEntity;
import com.example.demo.todo.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    private TodoService service;

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todoDTO){
        try {
            String tempUserId = "temp-user";
            TodoEntity entity = TodoDTO.toEntity(todoDTO);

            entity.setId(null);
            entity.setUserId(tempUserId);

            List<TodoEntity> todoEntities = service.create(entity);

            List<TodoDTO> dtos = todoEntities.stream().map(TodoDTO::new).collect(Collectors.toList());
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);
        } catch (Exception exception){
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(exception.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<?> retrieveTodoList() {
        String tempUserId = "temp-user";

        List<TodoEntity> todoEntities = service.retrieveTodo(tempUserId);

        List<TodoDTO> dtos = todoEntities.stream().map(TodoDTO::new).collect(Collectors.toList());
        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateTodo(@RequestBody TodoDTO todoDTO){
        try {
            String tempUserId = "temp-user";
            TodoEntity entity = TodoDTO.toEntity(todoDTO);

            entity.setUserId(tempUserId);

            List<TodoEntity> todoEntities = service.updateTodo(entity);

            List<TodoDTO> dtos = todoEntities.stream().map(TodoDTO::new).collect(Collectors.toList());
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);
        } catch (Exception exception){
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(exception.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO todoDTO){
        try {
            String tempUserId = "temp-user";
            TodoEntity entity = TodoDTO.toEntity(todoDTO);

            entity.setUserId(tempUserId);

            List<TodoEntity> todoEntities = service.deleteTodo(entity);

            List<TodoDTO> dtos = todoEntities.stream().map(TodoDTO::new).collect(Collectors.toList());
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);
        } catch (Exception exception){
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(exception.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}
