package org.solidarizr.manager.controller;

import org.solidarizr.manager.model.Category;
import org.solidarizr.manager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }


    @RequestMapping(path = "/category",  method = RequestMethod.POST)
    public Category save(@RequestBody Category category) {
        return service.save(category);
    }

    @RequestMapping(path = "/category", method = RequestMethod.DELETE)
    public void delete(@RequestBody Category category) {
        service.delete(category);
    }

    @RequestMapping(path = "/categories", method = RequestMethod.GET)
    public List<Category> getAll() {
        return service.getAll();
    }
}
