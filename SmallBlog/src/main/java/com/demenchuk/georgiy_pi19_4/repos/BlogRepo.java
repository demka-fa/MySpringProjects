package com.demenchuk.georgiy_pi19_4.repos;


import com.demenchuk.georgiy_pi19_4.entities.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

/**
 * The interface Blog repo.
 */
@Repository
public interface BlogRepo extends JpaRepository<BlogEntity, Long> {
    List<BlogEntity> findAll();
}
