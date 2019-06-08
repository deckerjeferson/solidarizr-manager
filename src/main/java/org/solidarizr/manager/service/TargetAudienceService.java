package org.solidarizr.manager.service;

import org.solidarizr.manager.model.Organization;
import org.solidarizr.manager.model.TargetAudience;
import org.solidarizr.manager.repository.OrganizationRepository;
import org.solidarizr.manager.repository.TargetAudienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TargetAudienceService {

    private TargetAudienceRepository repository;

    @Autowired
    public TargetAudienceService(TargetAudienceRepository repository) {
        this.repository = repository;
    }

    public TargetAudience save(TargetAudience targetAudience) {
        return repository.save(targetAudience);
    }

    public Boolean delete(Integer id) {
        Optional<TargetAudience> organizationToDelete = repository.findById(id);
        Boolean deleted = false;

        if(organizationToDelete.isPresent()){
            repository.delete(organizationToDelete.get());
            deleted = true;
        }

        return deleted;
    }

    public List<TargetAudience> getAll() {
        return (List<TargetAudience>) repository.findAll();
    }

    public List<TargetAudience> findTargetAudiencesByEventsWithCategoryId(Integer id){
        return repository.findTargetAudiencesByEventsWithCategoryId(id);
    }
}
