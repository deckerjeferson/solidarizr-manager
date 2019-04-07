package org.solidarizr.manager.controller;

import org.solidarizr.manager.model.Category;
import org.solidarizr.manager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class CategoryController {

    private CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    public Category save(Category categoryToBeInserted) {
        return null;
    }
}
