package org.solidarizr.manager.repository;

import org.solidarizr.manager.model.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EventRepository extends CrudRepository<Event, Integer> {
    public Optional<Event> findById(Integer id);
}
