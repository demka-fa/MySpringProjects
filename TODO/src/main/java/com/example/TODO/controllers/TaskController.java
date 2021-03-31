package com.example.TODO.controllers;

import com.example.TODO.entities.TaskEntity;
import com.example.TODO.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/api/tasks")
    public ResponseEntity<?> create(@RequestBody TaskEntity task) {
        taskService.create(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/api/tasks")
    public ResponseEntity<List<TaskEntity>> findAll() {
        final List<TaskEntity> taskList = taskService.findAll();
        return taskList != null && !taskList.isEmpty()
                ? new ResponseEntity<>(taskList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/tasks/{id}")
    public ResponseEntity<Optional<TaskEntity>> find(@PathVariable(name = "id") Long id) {
        final Optional<TaskEntity> task = taskService.find(id);
        return task != null
                ? new ResponseEntity<>(task, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/api/tasks/{id}")
    public ResponseEntity<?> updateTask(@PathVariable(name = "id") Long id, @RequestBody TaskEntity newTask) {
        return taskService.find(id).map(task -> {
            task.setCreate_date(newTask.getCreate_date());
            task.setChange_date(newTask.getChange_date());
            task.setName(newTask.getName());
            task.setDescription(newTask.getDescription());
            task.setComplete_date(newTask.getComplete_date());
            task.setCompleted(newTask.getCompleted());
            task.setUser(newTask.getUser());
            task.setCategories(newTask.getCategories());
            taskService.update(task);
            return new ResponseEntity<>(task, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());

    }

    @DeleteMapping("/api/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable(name = "id") Long id) {
        return taskService.find(id).map(task -> {
            taskService.delete(task);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }
}
