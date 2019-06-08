package org.solidarizr.manager.repository;

import org.solidarizr.manager.model.Category;
import org.solidarizr.manager.model.TargetAudience;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    public Optional<Category> findById(Integer id);

    @Query("select distinct c from category c " +
            "join c.events e " +
            "join e.targetAudience e " +
            "where e.id = ?1")
    public List<Category> findByEventsWithTargetAudienceId(Integer id);
}
