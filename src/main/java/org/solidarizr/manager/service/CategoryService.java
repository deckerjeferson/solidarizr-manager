package org.solidarizr.manager.service;

import org.solidarizr.manager.model.Category;
import org.solidarizr.manager.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category save(Category category){
        return repository.save(category);
    }

    public List<Category> getAll() {
        return (List<Category>) repository.findAll();
    }

    public void delete(Category categoryToDelete) {
        repository.delete(categoryToDelete);
    }
}
