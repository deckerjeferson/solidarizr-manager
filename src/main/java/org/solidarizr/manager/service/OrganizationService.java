package org.solidarizr.manager.service;

import org.solidarizr.manager.model.Organization;
import org.solidarizr.manager.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {

    private OrganizationRepository repository;

    @Autowired
    public OrganizationService(OrganizationRepository repository) {
        this.repository = repository;
    }

    public Organization save(Organization organization) {
        return repository.save(organization);
    }

    public Boolean delete(Integer id) {
        Optional<Organization> organizationToDelete = repository.findById(id);
        Boolean deleted = false;

        if(organizationToDelete.isPresent()){
            repository.delete(organizationToDelete.get());
            deleted = true;
        }

        return deleted;
    }

    public List<Organization> getAll() {
        return (List<Organization>) repository.findAll();
    }
}
