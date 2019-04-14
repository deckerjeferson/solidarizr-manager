package org.solidarizr.manager.controller;

import org.solidarizr.manager.model.Organization;
import org.solidarizr.manager.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(path = "/organization",method = RequestMethod.POST)
    public ResponseEntity<Organization> save(@RequestBody Organization organization) {
        Organization savedOrganization = service.save(organization);

        return ResponseEntity.ok(savedOrganization);
    }



    @RequestMapping(path = "/organization/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable(name="id") Integer id) {
        Boolean deleted = service.delete(id);

        ResponseEntity response;

        if(deleted) {
            response = ResponseEntity.ok().build();
        } else{
            response = ResponseEntity.notFound().build();
        }

        return response;
    }


    @RequestMapping(path = "/organizations", method = RequestMethod.GET)
    public ResponseEntity<List<Organization>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
