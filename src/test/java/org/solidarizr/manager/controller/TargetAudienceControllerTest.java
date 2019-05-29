package org.solidarizr.manager.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.solidarizr.manager.model.TargetAudience;
import org.solidarizr.manager.model.TargetAudience;
import org.solidarizr.manager.service.TargetAudienceService;
import org.solidarizr.manager.service.TargetAudienceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.solidarizr.manager.util.TargetAudienceFixtures.*;
import static org.solidarizr.manager.util.TargetAudienceFixtures.*;

@RunWith(MockitoJUnitRunner.class)
public class TargetAudienceControllerTest {

    private TargetAudienceController controller;

    @Mock
    private TargetAudienceService service;

    @Before
    public void setUp(){
        controller = new TargetAudienceController(service);
    }

    @Test
    public void should_insert_new_TARGET_AUDIENCE(){
        when(service.save(NOT_INSERTED_TARGET_AUDIENCE.getTargetAudience())).thenReturn(SAVED_TARGET_AUDIENCE.getTargetAudience());

        ResponseEntity<TargetAudience> result = controller.save(NOT_INSERTED_TARGET_AUDIENCE.getTargetAudience());

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        //assertThat(result.getBody()).isEqualTo(SAVED_TARGET_AUDIENCE.getTargetAudience());
    }

    @Test
    public void should_update_existing_TARGET_AUDIENCE(){
        when(service.save(TO_UPDATE_TARGET_AUDIENCE.getTargetAudience())).thenReturn(UPDATED_TARGET_AUDIENCE.getTargetAudience());

        ResponseEntity<TargetAudience> result = controller.save(TO_UPDATE_TARGET_AUDIENCE.getTargetAudience());

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(UPDATED_TARGET_AUDIENCE.getTargetAudience());
    }

    @Test
    public void should_delete_existing_TARGET_AUDIENCE(){
        when(service.delete(SAVED_TARGET_AUDIENCE.getTargetAudience().getId())).thenReturn(true);

        ResponseEntity result = controller.delete(1);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(service).delete(SAVED_TARGET_AUDIENCE.getTargetAudience().getId());
    }

    @Test
    public void should_return_404_when_try_to_delete_not_existing_TARGET_AUDIENCE(){
        when(service.delete(SAVED_TARGET_AUDIENCE.getTargetAudience().getId())).thenReturn(false);

        ResponseEntity result = controller.delete(1);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(service).delete(SAVED_TARGET_AUDIENCE.getTargetAudience().getId());
    }

    @Test
    public void should_return_all_caregories_in_database(){
        TargetAudience TargetAudience1 = TargetAudience.builder()
                .id(1)
                .name("TargetAudience 1").build();

        TargetAudience TargetAudience2 = TargetAudience.builder()
                .id(1)
                .name("TargetAudience 2").build();

        TargetAudience TargetAudience3 = TargetAudience.builder()
                .id(1)
                .name("TargetAudience 3").build();

        List<TargetAudience> allTargetAudiences = Arrays.asList(TargetAudience1, TargetAudience2, TargetAudience3);

        when(service.getAll()).thenReturn(allTargetAudiences);

        ResponseEntity<List<TargetAudience>> result = controller.getAll();

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).containsExactlyElementsOf(allTargetAudiences);
    }
}