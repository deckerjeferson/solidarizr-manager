package org.solidarizr.manager.controller;

import org.solidarizr.manager.model.Event;
import org.solidarizr.manager.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(path = "/event/{id}", method = RequestMethod.DELETE)
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

    @RequestMapping(path = "/events", method = RequestMethod.GET)
    public List<Event> getAll() {
        return service.getAll();
    }

    @RequestMapping(path = "/event", method = RequestMethod.GET)
    public ResponseEntity<List<Event>> getEventsBasedOnCategoryAndTargetAudience(
            @RequestParam(value = "category", required = true) Integer category,
            @RequestParam(value = "targetAudience", required = true) Integer targetAudience){

        List<Event> events = service.getEventsBasedOnCategoryAndTargetAudience(category, targetAudience);

        return ResponseEntity.ok(events);
    }
}
