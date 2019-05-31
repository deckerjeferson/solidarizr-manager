package org.solidarizr.manager.repository;

import org.solidarizr.manager.model.Category;
import org.solidarizr.manager.model.Event;
import org.solidarizr.manager.model.TargetAudience;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends CrudRepository<Event, Integer> {
    Optional<Event> findById(Integer id);

    List<Event> findByCategoryAndTargetAudience(Category category, TargetAudience targetAudience);
}
