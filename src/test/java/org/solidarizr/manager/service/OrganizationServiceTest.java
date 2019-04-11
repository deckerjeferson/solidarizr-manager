package org.solidarizr.manager.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.solidarizr.manager.model.Organization;
import org.solidarizr.manager.repository.OrganizationRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.solidarizr.manager.util.OragnizationFixtures.*;


@RunWith(MockitoJUnitRunner.class)
public class OrganizationServiceTest {

    @Mock
    private OrganizationRepository repository;

    private OrganizationService service;

    @Before
    public void setUp(){
        service = new OrganizationService(repository);
    }

    @Test
    public void should_insert_new_organization(){
        when(repository.save(NOT_INSERTED_ORGANIZATION.getOrganization())).thenReturn(SAVED_ORGANIZATION.getOrganization());

        Organization result = service.save(NOT_INSERTED_ORGANIZATION.getOrganization());

        assertThat(result).isEqualTo(SAVED_ORGANIZATION.getOrganization());
    }

    @Test
    public void should_update_existing_organization(){
        when(repository.save(TO_UPDATE_ORGANIZATION.getOrganization())).thenReturn(UPDATED_ORGANIZATION.getOrganization());

        Organization result = service.save(TO_UPDATE_ORGANIZATION.getOrganization());

        assertThat(result).isEqualTo(UPDATED_ORGANIZATION.getOrganization());
    }

    @Test
    public void should_delete_organization(){
        when(repository.findById(SAVED_ORGANIZATION.getOrganization().getId())).thenReturn(Optional.of(SAVED_ORGANIZATION.getOrganization()));

        Boolean result = service.delete(SAVED_ORGANIZATION.getOrganization().getId());

        assertThat(result).isTrue();
        verify(repository).delete(SAVED_ORGANIZATION.getOrganization());
    }

    @Test
    public void should_not_delete_organization_when_it_dont_exists(){
        when(repository.findById(SAVED_ORGANIZATION.getOrganization().getId())).thenReturn(Optional.empty());

        Boolean result = service.delete(SAVED_ORGANIZATION.getOrganization().getId());

        assertThat(result).isFalse();
        verify(repository, never()).delete(SAVED_ORGANIZATION.getOrganization());
    }

    @Test
    public void should_get_all_organizations(){
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

        when(repository.findAll()).thenReturn(allOrganizations);

        List<Organization> allOrganizationInDB = service.getAll();

        assertThat(allOrganizationInDB).containsExactlyInAnyOrderElementsOf(allOrganizations);
    }
}