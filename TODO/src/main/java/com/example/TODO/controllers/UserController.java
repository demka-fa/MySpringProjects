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
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/api/users")
    public ResponseEntity<?> create(@RequestBody UserEntity user){
        userService.create(user);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<UserEntity>> findAll(){
        final List<UserEntity> clientList = userService.findAll();
        return clientList != null && !clientList.isEmpty()
                ? new ResponseEntity<>(clientList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<Optional<UserEntity>> find(@PathVariable(name = "id") Long id){
        final Optional<UserEntity> user = userService.find(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/api/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(name = "id") Long id, @RequestBody UserEntity newUser) {
        return userService.find(id).map(client -> {
            client.setFirstName(newUser.getFirstName());
            client.setSecondName(newUser.getSecondName());
            client.setLastName(newUser.getLastName());
            client.setLogin(newUser.getLogin());
            client.setCreate_date(newUser.getCreate_date());
            client.setBirthday(newUser.getBirthday());
            client.setChange_date(newUser.getChange_date());
            client.setTasks(newUser.getTasks());
            userService.update(client);
            return new ResponseEntity<>(client, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());

    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) {
        return userService.find(id).map(client -> {
            userService.delete(client);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }


}
