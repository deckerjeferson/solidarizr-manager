package org.solidarizr.manager.controller;

import org.solidarizr.manager.model.Category;
import org.solidarizr.manager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping
public class CategoryController {

    private CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }


    @RequestMapping(method = RequestMethod.POST)
    public Category save(@RequestBody Category category) {
        return service.save(category);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@RequestBody Category category) {
        service.delete(category);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Category> getAll() {
        return service.getAll();
    }
}
