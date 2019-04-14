package org.solidarizr.manager.controller;

import org.solidarizr.manager.model.Category;
import org.solidarizr.manager.model.Event;
import org.solidarizr.manager.service.CategoryService;
import org.solidarizr.manager.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {

    private EventService service;

    @Autowired
    public EventController(EventService service) {
        this.service = service;
    }


    @RequestMapping(path = "/event",  method = RequestMethod.POST)
    public ResponseEntity<Event> save(@RequestBody Event event) {
        return ResponseEntity.ok(service.save(event));
    }

    @RequestMapping(path = "/event", method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestBody Event event) {
        ResponseEntity response;
        Boolean deleted = service.delete(event.getId());

        if(deleted){
            response = ResponseEntity.ok().build();
        } else {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    @RequestMapping(path = "/events", method = RequestMethod.GET)
    public List<Event> getAll() {
        return service.getAll();
    }
}
