package org.solidarizr.manager.service;

import org.solidarizr.manager.model.Category;
import org.solidarizr.manager.model.Event;
import org.solidarizr.manager.repository.CategoryRepository;
import org.solidarizr.manager.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    EventRepository repository;

    @Autowired
    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public Event save(Event event){
        return repository.save(event);
    }

    public List<Event> getAll() {
        return (List<Event>) repository.findAll();
    }

    public Boolean delete(Integer id) {
        Optional<Event> eventToDelete = repository.findById(id);
        Boolean deleted = false;

        if(eventToDelete.isPresent()) {
            repository.delete(eventToDelete.get());
            deleted = true;
        }

        return deleted;
    }
}
