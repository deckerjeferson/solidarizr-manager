package org.solidarizr.manager.util;

import lombok.Getter;
import org.solidarizr.manager.model.Organization;

@Getter
public enum OragnizationFixtures {
    NOT_INSERTED_ORGANIZATION(Organization.builder()
    .name("Organization 1").build()),
    SAVED_ORGANIZATION(Organization.builder()
    .id(1)
    .name("Organization 1").build()),
    UPDATED_ORGANIZATION(Organization.builder()
    .id(1)
    .name("Organization Updated").build()),
    TO_UPDATE_ORGANIZATION(Organization.builder()
            .id(1)
            .name("Organization Updated").build());

    private Organization organization;

    OragnizationFixtures(Organization organization) {
        this.organization = organization;
    }
}
