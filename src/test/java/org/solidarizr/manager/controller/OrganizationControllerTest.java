package org.solidarizr.manager.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.solidarizr.manager.model.Organization;
import org.solidarizr.manager.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.solidarizr.manager.util.OrganizationFixtures.*;

@RunWith(MockitoJUnitRunner.class)
public class OrganizationControllerTest {

    private OrganizationController controller;

    @Mock
    private OrganizationService service;

    @Before
    public void setUp(){
        controller = new OrganizationController(service);
    }

    @Test
    public void should_insert_new_organization(){
        when(service.save(NOT_INSERTED_ORGANIZATION.getOrganization())).thenReturn(SAVED_ORGANIZATION.getOrganization());

        ResponseEntity<Organization> result = controller.save(NOT_INSERTED_ORGANIZATION.getOrganization());

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(SAVED_ORGANIZATION.getOrganization());
    }

    @Test
    public void should_update_existing_organization(){
        when(service.save(TO_UPDATE_ORGANIZATION.getOrganization())).thenReturn(UPDATED_ORGANIZATION.getOrganization());

        ResponseEntity<Organization> result = controller.save(TO_UPDATE_ORGANIZATION.getOrganization());

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(UPDATED_ORGANIZATION.getOrganization());
    }

    @Test
    public void should_delete_existing_organization(){
        when(service.delete(SAVED_ORGANIZATION.getOrganization().getId())).thenReturn(true);

        ResponseEntity result = controller.delete(SAVED_ORGANIZATION.getOrganization());

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(service).delete(SAVED_ORGANIZATION.getOrganization().getId());
    }

    @Test
    public void should_return_404_when_try_to_delete_not_existing_organization(){
        when(service.delete(SAVED_ORGANIZATION.getOrganization().getId())).thenReturn(false);

        ResponseEntity result = controller.delete(SAVED_ORGANIZATION.getOrganization());

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(service).delete(SAVED_ORGANIZATION.getOrganization().getId());
    }

    @Test
    public void should_return_all_caregories_in_database(){
        Organization organization1 = Organization.builder()
                .id(1)
                .name("Organization 1").build();

        Organization organization2 = Organization.builder()
                .id(1)
                .name("Organization 2").build();

        Organization organization3 = Organization.builder()
                .id(1)
                .name("Organization 3").build();

        List<Organization> allOrganizations = Arrays.asList(organization1, organization2, organization3);

        when(service.getAll()).thenReturn(allOrganizations);

        ResponseEntity<List<Organization>> result = controller.getAll();

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).containsExactlyElementsOf(allOrganizations);
    }
}