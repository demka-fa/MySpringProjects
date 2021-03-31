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
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService itemService;

    @Autowired
    public TaskController(TaskService itemService) {
        this.itemService = itemService;
    }

    @PostMapping()
    public ResponseEntity<?> createItem(@RequestBody TaskEntity item) {
        itemService.create(item);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> findAllItems() {
        List<TaskEntity> itemList = itemService.findAll();
        if (itemList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        Optional<TaskEntity> currentItem = itemService.find(id);
        if (currentItem.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentItem.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(@PathVariable(name = "id") Long id, @RequestBody TaskEntity newItem) {
        Optional<TaskEntity> currentItemOptional = itemService.find(id);
        if (currentItemOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        TaskEntity currentItem = currentItemOptional.get();

        currentItem.setCreatedDate(newItem.getCreatedDate());
        currentItem.setChangedDate(newItem.getChangedDate());
        currentItem.setTitle(newItem.getTitle());
        currentItem.setDescription(newItem.getDescription());
        currentItem.setCompletedDate(newItem.getCompletedDate());
        currentItem.setIsReady(newItem.getIsReady());
        currentItem.setUser(newItem.getUser());
        currentItem.setCategories(newItem.getCategories());

        itemService.update(currentItem);
        return new ResponseEntity<>(currentItem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable(name = "id") Long id) {
        Optional<TaskEntity> currentItemOptional = itemService.find(id);
        if (currentItemOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        itemService.delete(currentItemOptional.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}