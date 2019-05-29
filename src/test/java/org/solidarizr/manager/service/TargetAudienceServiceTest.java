package org.solidarizr.manager.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.solidarizr.manager.model.Organization;
import org.solidarizr.manager.model.TargetAudience;
import org.solidarizr.manager.repository.OrganizationRepository;
import org.solidarizr.manager.repository.TargetAudienceRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.solidarizr.manager.util.OrganizationFixtures.*;
import static org.solidarizr.manager.util.TargetAudienceFixtures.*;


@RunWith(MockitoJUnitRunner.class)
public class TargetAudienceServiceTest {

    @Mock
    private TargetAudienceRepository repository;

    private TargetAudienceService service;

    @Before
    public void setUp(){
        service = new TargetAudienceService(repository);
    }

    @Test
    public void should_insert_new_TARGET_AUDIENCE(){
        when(repository.save(NOT_INSERTED_TARGET_AUDIENCE.getTargetAudience())).thenReturn(SAVED_TARGET_AUDIENCE.getTargetAudience());

        TargetAudience result = service.save(NOT_INSERTED_TARGET_AUDIENCE.getTargetAudience());

        assertThat(result).isEqualTo(SAVED_TARGET_AUDIENCE.getTargetAudience());
    }

    @Test
    public void should_update_existing_TARGET_AUDIENCE(){
        when(repository.save(TO_UPDATE_TARGET_AUDIENCE.getTargetAudience())).thenReturn(UPDATED_TARGET_AUDIENCE.getTargetAudience());

        TargetAudience result = service.save(TO_UPDATE_TARGET_AUDIENCE.getTargetAudience());

        assertThat(result).isEqualTo(UPDATED_TARGET_AUDIENCE.getTargetAudience());
    }

    @Test
    public void should_delete_TARGET_AUDIENCE(){
        when(repository.findById(SAVED_TARGET_AUDIENCE.getTargetAudience().getId())).thenReturn(Optional.of(SAVED_TARGET_AUDIENCE.getTargetAudience()));

        Boolean result = service.delete(SAVED_TARGET_AUDIENCE.getTargetAudience().getId());

        assertThat(result).isTrue();
        verify(repository).delete(SAVED_TARGET_AUDIENCE.getTargetAudience());
    }

    @Test
    public void should_not_delete_TARGET_AUDIENCE_when_it_dont_exists(){
        when(repository.findById(SAVED_TARGET_AUDIENCE.getTargetAudience().getId())).thenReturn(Optional.empty());

        Boolean result = service.delete(SAVED_TARGET_AUDIENCE.getTargetAudience().getId());

        assertThat(result).isFalse();
        verify(repository, never()).delete(SAVED_TARGET_AUDIENCE.getTargetAudience());
    }

    @Test
    public void should_get_all_TARGET_AUDIENCEs(){
        TargetAudience targetAudience1 = TargetAudience.builder()
                    .id(1)
                    .name("TargetAudience 1").build();

        TargetAudience targetAudience2 = TargetAudience.builder()
                    .id(1)
                    .name("TargetAudience 2").build();

        TargetAudience targetAudience3 = TargetAudience.builder()
                    .id(1)
                    .name("TargetAudience 3").build();

            List<TargetAudience> allTargetAudiences = Arrays.asList(targetAudience1, targetAudience2, targetAudience3);

        when(repository.findAll()).thenReturn(allTargetAudiences);

        List<TargetAudience> allOrganizationInDB = service.getAll();

        assertThat(allOrganizationInDB).containsExactlyInAnyOrderElementsOf(allOrganizationInDB);
    }
}