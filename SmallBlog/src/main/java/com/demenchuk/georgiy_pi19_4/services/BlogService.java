package com.demenchuk.georgiy_pi19_4.services;

import com.demenchuk.georgiy_pi19_4.entities.BlogEntity;
import com.demenchuk.georgiy_pi19_4.repos.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * The type Blog service.
 */
@Service
public class BlogService {

    @Autowired
    private BlogRepo blogRepo;

    /**
     * Create.
     *
     * @param blog the blog
     */
    public void create(BlogEntity blog) {
        blogRepo.save(blog);
    }

    /**
     * Update.
     *
     * @param oldObj the old obj
     * @param newObj the new obj
     */
    public void update(BlogEntity oldObj, BlogEntity newObj) {
        blogRepo.delete(oldObj);
        blogRepo.save(newObj);
    }

    /**
     * Delete.
     *
     * @param blog the blog
     */
    public void delete(BlogEntity blog) {
        blogRepo.delete(blog);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<BlogEntity> findAll() {
        return blogRepo.findAll();
    }

    /**
     * Find optional.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<BlogEntity> find(Long id) {
        return blogRepo.findById(id);
    }

}
