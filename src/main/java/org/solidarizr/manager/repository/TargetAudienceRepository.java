package org.solidarizr.manager.repository;

import org.solidarizr.manager.model.Organization;
import org.solidarizr.manager.model.TargetAudience;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TargetAudienceRepository extends CrudRepository<TargetAudience, Integer> {
    public Optional<TargetAudience> findById(Integer id);
}
