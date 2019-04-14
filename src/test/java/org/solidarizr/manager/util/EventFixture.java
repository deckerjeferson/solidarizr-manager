package org.solidarizr.manager.util;

import lombok.Getter;
import org.solidarizr.manager.model.Event;

import static org.solidarizr.manager.util.CategoryFixture.SAVED_CATEGORY;
import static org.solidarizr.manager.util.OrganizationFixtures.SAVED_ORGANIZATION;

@Getter
public enum EventFixture {
    NOT_INSERTED_EVENT(Event.builder()
            .name("Event 1")
            .category(SAVED_CATEGORY.getCategory())
            .organization(SAVED_ORGANIZATION.getOrganization())
            .build()),
    SAVED_EVENT(Event.builder()
            .name("Event 1")
            .category(SAVED_CATEGORY.getCategory())
            .organization(SAVED_ORGANIZATION.getOrganization())
            .id(1).build()),
    TO_UPDATE_EVENT(Event.builder()
            .name("Event 1 Modified")
            .category(SAVED_CATEGORY.getCategory())
            .organization(SAVED_ORGANIZATION.getOrganization())
            .id(1).build()),
    UPDATED_EVENT(Event.builder()
            .name("Event 1 Modified")
            .category(SAVED_CATEGORY.getCategory())
            .organization(SAVED_ORGANIZATION.getOrganization())
            .id(1).build());

    private Event event;

    EventFixture(Event event) {
        this.event = event;
    }
}
