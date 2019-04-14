package org.solidarizr.manager.util;

import lombok.Getter;
import org.solidarizr.manager.model.Category;

@Getter
public enum CategoryFixture {
    NOT_INSERTED_CATEGORY(Category.builder()
            .name("Category 1").build()),
    SAVED_CATEGORY(Category.builder()
            .id(1)
            .name("Category 1")
            .build()),
    TO_UPDATE_CATEGORY(Category.builder()
            .id(1)
            .name("Category 1 Modified").build()),
    UPDATED_CATEGORY(Category.builder()
            .id(1)
            .name("Category 1 Modified").build());

    private Category category;

    CategoryFixture(Category category) {
        this.category = category;
    }
}
