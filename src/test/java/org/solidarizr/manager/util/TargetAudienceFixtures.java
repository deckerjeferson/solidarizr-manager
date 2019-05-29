package org.solidarizr.manager.util;

import lombok.Getter;
import org.solidarizr.manager.model.Organization;
import org.solidarizr.manager.model.TargetAudience;

@Getter
public enum TargetAudienceFixtures {
        NOT_INSERTED_TARGET_AUDIENCE(TargetAudience.builder()
        .name("Target Audience 1").build()),
        SAVED_TARGET_AUDIENCE(TargetAudience.builder()
        .id(1)
        .name("Target Audience 1").build()),
        UPDATED_TARGET_AUDIENCE(TargetAudience.builder()
        .id(1)
        .name("Target Audience Updated").build()),
        TO_UPDATE_TARGET_AUDIENCE(TargetAudience.builder()
                .id(1)
                .name("Target Audience Updated").build());

    private TargetAudience targetAudience;

    TargetAudienceFixtures(TargetAudience targetAudience) {
        this.targetAudience = targetAudience;
    }
}
