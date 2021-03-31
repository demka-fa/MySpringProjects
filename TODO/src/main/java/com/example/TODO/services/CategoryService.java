package com.example.TODO.services;

import com.example.TODO.entities.CategoryEntity;
import com.example.TODO.repos.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo){
        this.categoryRepo = categoryRepo;
    }

    public void create(CategoryEntity category){
        categoryRepo.save(category);
    }

    public void update(CategoryEntity category){
        categoryRepo.save(category);
    }

    public void delete(CategoryEntity category){
        categoryRepo.delete(category);
    }

    public List<CategoryEntity> findAll(){
        return categoryRepo.findAll();
    }

    public Optional<CategoryEntity> find(Long id){
        return categoryRepo.findById(id);
    }

}


