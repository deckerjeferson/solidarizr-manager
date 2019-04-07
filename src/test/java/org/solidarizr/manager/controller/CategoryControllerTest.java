package org.solidarizr.manager.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.solidarizr.manager.model.Category;
import org.solidarizr.manager.service.CategoryService;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    @Mock
    private CategoryService service;

    private CategoryController controller;

    public void setUp(){
        controller = new CategoryController(service);
    }

    @Test
    public void should_insert_category(){
        Category categoryToBeInserted = Category.builder()
                .name("Category 1").build();

        Category categorySaved = Category.builder()
                .id(1)
                .name("Category 1")
                .build();

        when(service.save(categoryToBeInserted)).thenReturn(categorySaved);

        Category result = controller.save(categoryToBeInserted);

        assertThat(result).isEqualTo(categorySaved);
    }
}