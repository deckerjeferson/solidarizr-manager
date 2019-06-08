package org.solidarizr.manager.controller;

import org.solidarizr.manager.model.Category;
import org.solidarizr.manager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(path = "/category/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable(name="id") Integer id) {
        ResponseEntity response;
        Boolean deleted = service.delete(id);

        if(deleted){
            response = ResponseEntity.ok().build();
        } else {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    @RequestMapping(path = "/categories", method = RequestMethod.GET)
    public List<Category> getAll() {
        return service.getAll();
    }

    @RequestMapping(path = "category/targetAudience/{id}")
    public ResponseEntity<List<Category>> findByEventsWithTargetAudienceId(@PathVariable(name = "id", required = true) Integer id){
        return ResponseEntity.ok().body(service.findByEventsWithTargetAudienceId(id));
    }
}
