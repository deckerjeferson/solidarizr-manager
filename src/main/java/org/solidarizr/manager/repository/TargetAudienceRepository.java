package org.solidarizr.manager.repository;

import org.solidarizr.manager.model.Organization;
import org.solidarizr.manager.model.TargetAudience;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TargetAudienceRepository extends CrudRepository<TargetAudience, Integer> {
    public Optional<TargetAudience> findById(Integer id);

    @Query("select t from targetAudience t " +
            "join t.events e " +
            "join e.category c " +
            "where c.id = ?1")
    public List<TargetAudience> findTargetAudiencesByEventsWithCategoryId(Integer id);
}
