package org.solidarizr.manager.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.solidarizr.manager.model.Category;
import org.solidarizr.manager.model.Event;
import org.solidarizr.manager.repository.CategoryRepository;
import org.solidarizr.manager.repository.EventRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.solidarizr.manager.util.CategoryFixture.*;
import static org.solidarizr.manager.util.EventFixture.*;
import static org.solidarizr.manager.util.OrganizationFixtures.SAVED_ORGANIZATION;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {
    @Mock
    EventRepository repository;

    EventService service;

    @Before
    public void setUp(){
        service = new EventService(repository);
    }

    @Test
    public void should_insert_event(){
        when(repository.save(NOT_INSERTED_EVENT.getEvent())).thenReturn(SAVED_EVENT.getEvent());

        Event result = service.save(NOT_INSERTED_EVENT.getEvent());

        assertThat(result).isEqualTo(SAVED_EVENT.getEvent());
    }

    @Test
    public void should_update_category(){
        when(repository.save(TO_UPDATE_EVENT.getEvent())).thenReturn(UPDATED_EVENT.getEvent());

        Event result = service.save(TO_UPDATE_EVENT.getEvent());

        assertThat(result).isEqualTo(UPDATED_EVENT.getEvent());
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

        when(repository.findAll()).thenReturn(allEvent);

        List<Event> result = service.getAll();

        assertThat(result).containsExactlyInAnyOrderElementsOf(allEvent);
    }

    @Test
    public void should_delete_category_when_it_exists(){
        when(repository.findById(1)).thenReturn(Optional.of(SAVED_EVENT.getEvent()));
        Boolean deleted = service.delete(SAVED_EVENT.getEvent().getId());

        verify(repository).delete(SAVED_EVENT.getEvent());
        assertThat(deleted).isTrue();
    }

    @Test
    public void should_not_delete_category_when_it_doesnt_exists(){
        when(repository.findById(1)).thenReturn(Optional.empty());
        Boolean deleted = service.delete(SAVED_EVENT.getEvent().getId());

        verify(repository, never()).delete(SAVED_EVENT.getEvent());
        assertThat(deleted).isFalse();
    }
}