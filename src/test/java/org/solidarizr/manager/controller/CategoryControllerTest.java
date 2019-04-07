package org.solidarizr.manager.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.solidarizr.manager.model.Category;
import org.solidarizr.manager.service.CategoryService;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    @Mock
    private CategoryService service;

    private CategoryController controller;

    @Before
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

    @Test
    public void should_update_category(){
        Category categoryToBeUpdated = Category.builder()
                .id(1)
                .name("Category 1 Modified").build();

        Category categorySaved = Category.builder()
                .id(1)
                .name("Category 1 Modified")
                .build();

        when(service.save(categoryToBeUpdated)).thenReturn(categorySaved);

        Category result = controller.save(categoryToBeUpdated);

        assertThat(result).isEqualTo(categorySaved);
    }

    @Test
    public void should_delete_category(){
        Category category = Category.builder()
                .id(1)
                .name("Category 1 Modified")
                .build();

        controller.delete(category);

        verify(service).delete(category);
    }

    @Test
    public void should_list_all_caterogies(){
        List<Category> allCategories = Arrays.asList(
                Category.builder()
                        .id(1)
                        .name("Category 1").build(),
                Category.builder()
                        .id(1)
                        .name("Category 2").build());


        when(service.getAll()).thenReturn(allCategories);

        List<Category> result = controller.getAll();

        assertThat(result).containsExactlyElementsOf(allCategories);
    }
}