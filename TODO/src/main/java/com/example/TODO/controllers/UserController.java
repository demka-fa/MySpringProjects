package com.example.TODO.controllers;

import com.example.TODO.entities.UserEntity;
import com.example.TODO.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService itemService;

    @Autowired
    public UserController(UserService itemService) {
        this.itemService = itemService;
    }

    @PostMapping()
    public ResponseEntity<?> createItem(@RequestBody UserEntity item) {
        itemService.create(item);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> findAllItems() {
        List<UserEntity> itemList = itemService.findAll();
        if (itemList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        Optional<UserEntity> currentItem = itemService.find(id);
        if (currentItem.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentItem.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(@PathVariable(name = "id") Long id, @RequestBody UserEntity newItem) {
        Optional<UserEntity> currentItemOptional = itemService.find(id);
        if (currentItemOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserEntity currentItem = currentItemOptional.get();

        currentItem.setFirstName(newItem.getFirstName());
        currentItem.setSecondName(newItem.getSecondName());
        currentItem.setLastName(newItem.getLastName());
        currentItem.setLogin(newItem.getLogin());
        currentItem.setCreatedDate(newItem.getCreatedDate());
        currentItem.setBirthday(newItem.getBirthday());
        currentItem.setChangedDate(newItem.getChangedDate());
        currentItem.setTasks(newItem.getTasks());


        itemService.update(currentItem);
        return new ResponseEntity<>(currentItem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable(name = "id") Long id) {
        Optional<UserEntity> currentItemOptional = itemService.find(id);
        if (currentItemOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        itemService.delete(currentItemOptional.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
