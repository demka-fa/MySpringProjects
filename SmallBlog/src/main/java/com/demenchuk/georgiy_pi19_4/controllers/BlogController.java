package com.demenchuk.georgiy_pi19_4.controllers;

import com.demenchuk.georgiy_pi19_4.entities.BlogEntity;
import com.demenchuk.georgiy_pi19_4.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The type Blog controller.
 */
@RestController
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;

    /**
     * Instantiates a new Blog controller.
     *
     * @param blogService the blog service
     */
    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * Create blog response entity.
     *
     * @param blog the blog
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<?> createBlog(@RequestBody BlogEntity blog) {
        blogService.create(blog);
        return new ResponseEntity<>(blog, HttpStatus.CREATED);
    }

    /**
     * Gets all blogs.
     *
     * @return the all blogs
     */
    @GetMapping
    public ResponseEntity<List<BlogEntity>> getAllBlogs() {
        List<BlogEntity> results = blogService.findAll();
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    /**
     * Gets blog by id.
     *
     * @param id the id
     * @return the blog by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getBlogById(@PathVariable Long id) {
        Optional<BlogEntity> result = blogService.find(id);
        if (result.isPresent()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete blog response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Long id) {

        Optional<BlogEntity> result = blogService.find(id);

        //Если есть такой id
        if (result.isPresent()) {
            blogService.delete(result.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        //Если нет такого id
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Edit blog response entity.
     *
     * @param newItem the new item
     * @param id      the id
     * @return the response entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> editBlog(@RequestBody BlogEntity newItem, @PathVariable Long id) {
        Optional<BlogEntity> oldItem = blogService.find(id);
        if (oldItem.isPresent()) {
            blogService.update(oldItem.get(), newItem);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}