package org.solidarizr.manager.controller;

import org.solidarizr.manager.model.Organization;
import org.solidarizr.manager.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class OrganizationController {

    private OrganizationService service;

    @Autowired
    public OrganizationController(@RequestBody OrganizationService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Organization> save(@RequestBody Organization organization) {
        Organization savedOrganization = service.save(organization);

        return ResponseEntity.ok(savedOrganization);
    }


    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestBody Organization organization) {
        Boolean deleted = service.delete(organization.getId());

        ResponseEntity response;

        if(deleted) {
            response = ResponseEntity.ok().build();
        } else{
            response = ResponseEntity.notFound().build();
        }

        return response;
    }


    @RequestMapping(name = "/organizations", method = RequestMethod.GET)
    public ResponseEntity<List<Organization>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
