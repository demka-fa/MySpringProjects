package com.example.TODO.controllers;


import com.example.TODO.entities.CategoryEntity;
import com.example.TODO.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/api/categories")
    public ResponseEntity<?> create(@RequestBody CategoryEntity category) {
        categoryService.create(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/api/categories")
    public ResponseEntity<List<CategoryEntity>> findAll() {
        final List<CategoryEntity> categoryList = categoryService.findAll();
        return categoryList != null && !categoryList.isEmpty()
                ? new ResponseEntity<>(categoryList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/categories/{id}")
    public ResponseEntity<Optional<CategoryEntity>> find(@PathVariable(name = "id") Long id) {
        final Optional<CategoryEntity> category = categoryService.find(id);
        return category != null
                ? new ResponseEntity<>(category, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/api/categories/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable(name = "id") Long id, @RequestBody CategoryEntity newCategory) {
        return categoryService.find(id).map(category -> {
            category.setName(newCategory.getName());
            category.setChange_date(newCategory.getChange_date());
            category.setCreate_date(newCategory.getCreate_date());
            category.setTasks(newCategory.getTasks());
            categoryService.update(category);
            return new ResponseEntity<>(category, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @DeleteMapping("/api/categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable(name = "id") Long id) {
        return categoryService.find(id).map(category -> {
            categoryService.delete(category);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }
}

