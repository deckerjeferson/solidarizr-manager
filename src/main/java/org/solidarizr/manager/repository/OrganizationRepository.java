package org.solidarizr.manager.repository;

import org.solidarizr.manager.model.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, Integer> {
    public Optional<Organization> findById(Integer id);
}
