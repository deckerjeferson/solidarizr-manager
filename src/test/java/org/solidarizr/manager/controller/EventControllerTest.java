package org.solidarizr.manager.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.solidarizr.manager.model.Event;
import org.solidarizr.manager.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.solidarizr.manager.util.CategoryFixture.SAVED_CATEGORY;
import static org.solidarizr.manager.util.EventFixture.*;
import static org.solidarizr.manager.util.OrganizationFixtures.SAVED_ORGANIZATION;

@RunWith(MockitoJUnitRunner.class)
public class EventControllerTest {
    @Mock
    EventService service;

    EventController controller;

    @Before
    public void setUp(){
        controller = new EventController(service);
    }

    @Test
    public void should_insert_event(){
        when(service.save(NOT_INSERTED_EVENT.getEvent())).thenReturn(SAVED_EVENT.getEvent());

        ResponseEntity<Event> response = controller.save(NOT_INSERTED_EVENT.getEvent());

        assertThat(response.getBody()).isEqualTo(SAVED_EVENT.getEvent());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void should_update_category(){
        when(service.save(TO_UPDATE_EVENT.getEvent())).thenReturn(UPDATED_EVENT.getEvent());

        ResponseEntity<Event> response = controller.save(TO_UPDATE_EVENT.getEvent());

        assertThat(response.getBody()).isEqualTo(UPDATED_EVENT.getEvent());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void should_return_all_categories(){
        List<Event> allEvent = Arrays.asList(
                Event.builder()
                        .id(1)
                        .name("Event 1")
                        .category(SAVED_CATEGORY.getCategory())
                        .organization(SAVED_ORGANIZATION.getOrganization())
                .build(),
                Event.builder()
                        .id(1)
                        .name("Event 2")
                        .category(SAVED_CATEGORY.getCategory())
                        .organization(SAVED_ORGANIZATION.getOrganization())
                        .build());

        when(service.getAll()).thenReturn(allEvent);

        List<Event> result = controller.getAll();

        assertThat(result).containsExactlyInAnyOrderElementsOf(allEvent);
    }

    @Test
    public void should_delete_existing_organization_with_OK_http_status(){
        when(service.delete(SAVED_EVENT.getEvent().getId())).thenReturn(true);
        ResponseEntity response = controller.delete(1);

        verify(service).delete(SAVED_EVENT.getEvent().getId());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void should_return_404_when_try_to_delete_not_existing_event(){
        when(service.delete(SAVED_EVENT.getEvent().getId())).thenReturn(false);
        ResponseEntity response = controller.delete(1);

        verify(service).delete(SAVED_EVENT.getEvent().getId());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}