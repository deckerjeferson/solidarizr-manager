package org.solidarizr.manager.service;

import org.solidarizr.manager.model.Category;
import org.solidarizr.manager.model.Event;
import org.solidarizr.manager.model.TargetAudience;
import org.solidarizr.manager.repository.CategoryRepository;
import org.solidarizr.manager.repository.EventRepository;
import org.solidarizr.manager.repository.TargetAudienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    EventRepository repository;
    CategoryRepository categoryRepository;
    TargetAudienceRepository targetAudienceRepository;

    @Autowired
    public EventService(EventRepository repository, CategoryRepository categoryRepository, TargetAudienceRepository targetAudienceRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.targetAudienceRepository = targetAudienceRepository;
    }

    public Event save(Event event){
        return repository.save(event);
    }

    public List<Event> getAll() {
        return repository.findAllByOrderByIdAsc();
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

    public List<Event> getEventsBasedOnCategoryAndTargetAudience(Integer categoryId, Integer targetAudienceId) {
        List<Event> events = new ArrayList<>();


        Optional<Category> category = categoryRepository.findById(categoryId);
        Optional<TargetAudience> targetAudience = targetAudienceRepository.findById(targetAudienceId);

        if(category.isPresent() && targetAudience.isPresent()) {
            events = repository.findByCategoryAndTargetAudience(category.get(), targetAudience.get());
        }

        return events;
    }

    public Optional<Event> getById(Integer id){
        return repository.findById(id);
    }
}
