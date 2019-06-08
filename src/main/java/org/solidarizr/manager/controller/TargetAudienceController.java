package org.solidarizr.manager.controller;

import org.solidarizr.manager.model.Organization;
import org.solidarizr.manager.model.TargetAudience;
import org.solidarizr.manager.service.OrganizationService;
import org.solidarizr.manager.service.TargetAudienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TargetAudienceController {

    private TargetAudienceService service;

    @Autowired
    public TargetAudienceController(@RequestBody TargetAudienceService service) {
        this.service = service;
    }

    @RequestMapping(path = "/targetAudience",method = RequestMethod.POST)
    public ResponseEntity<TargetAudience> save(@RequestBody TargetAudience targetAudience) {
        TargetAudience toSaveTargetAudience = service.save(targetAudience);

        return ResponseEntity.ok(targetAudience);
    }



    @RequestMapping(path = "/targetAudience/{id}", method = RequestMethod.DELETE)
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


    @RequestMapping(path = "/targetAudiences", method = RequestMethod.GET)
    public ResponseEntity<List<TargetAudience>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @RequestMapping(path = "/targetAudience/category/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<TargetAudience>> getTargetAudiencesByEventsWithCategoryId(@PathVariable(name="id", required = true) Integer id){
        return ResponseEntity.ok().body(service.findTargetAudiencesByEventsWithCategoryId(id));
    }
}
