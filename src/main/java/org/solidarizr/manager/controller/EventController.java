package org.solidarizr.manager.controller;

import io.swagger.annotations.ApiOperation;
import org.solidarizr.manager.model.Event;
import org.solidarizr.manager.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EventController {

    private EventService service;

    @Autowired
    public EventController(EventService service) {
        this.service = service;
    }


    @ApiOperation(value = "Saves volunteer project")
    @RequestMapping(path = "/event",  method = RequestMethod.POST)
    public ResponseEntity<Event> save(@RequestBody Event event) {
        return ResponseEntity.ok(service.save(event));
    }

    @ApiOperation(value = "Deletes volunteer project")
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

    @RequestMapping(path = "/event/{id}", method = RequestMethod.GET)
    public ResponseEntity<Event> findById(@PathVariable(name = "id") Integer id){
        ResponseEntity response;
        Optional<Event> event = service.getById(id);

        if(event.isPresent()){
            response = ResponseEntity.ok(event.get());
        } else {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    @ApiOperation(value = "Finds all volunteer projects")
    @RequestMapping(path = "/events", method = RequestMethod.GET)
    public List<Event> getAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Finds specific volunteer project", httpMethod = "GET")
    @RequestMapping(path = "/event", method = RequestMethod.GET)
    public ResponseEntity<List<Event>> getEventsBasedOnCategoryAndTargetAudience(
            @RequestParam(value = "category", required = true) Integer category,
            @RequestParam(value = "targetAudience", required = true) Integer targetAudience){

        List<Event> events = service.getEventsBasedOnCategoryAndTargetAudience(category, targetAudience);

        return ResponseEntity.ok(events);
    }
}
