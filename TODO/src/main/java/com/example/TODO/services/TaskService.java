package com.example.TODO.services;

import com.example.TODO.entities.TaskEntity;
import com.example.TODO.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepo taskRepo;

    @Autowired
    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public void create(TaskEntity task) {
        taskRepo.save(task);
    }

    public void update(TaskEntity task) {
        taskRepo.save(task);
    }

    public void delete(TaskEntity task) {
        taskRepo.delete(task);
    }

    public List<TaskEntity> findAll() {
        return taskRepo.findAll();
    }

    public Optional<TaskEntity> find(Long id) {
        return taskRepo.findById(id);
    }

}