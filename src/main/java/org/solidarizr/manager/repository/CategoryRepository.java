package org.solidarizr.manager.repository;

import org.solidarizr.manager.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    public Optional<Category> findById(Integer id);
}
