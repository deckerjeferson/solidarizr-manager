package org.solidarizr.manager.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.solidarizr.manager.model.Category;
import org.solidarizr.manager.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.solidarizr.manager.util.CategoryFixture.*;
import static org.solidarizr.manager.util.OrganizationFixtures.SAVED_ORGANIZATION;


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
        when(service.save(NOT_INSERTED_CATEGORY.getCategory())).thenReturn(SAVED_CATEGORY.getCategory());

        Category result = controller.save(NOT_INSERTED_CATEGORY.getCategory());

        assertThat(result).isEqualTo(SAVED_CATEGORY.getCategory());
    }

    @Test
    public void should_update_category(){
        when(service.save(TO_UPDATE_CATEGORY.getCategory())).thenReturn(UPDATED_CATEGORY.getCategory());

        Category result = controller.save(TO_UPDATE_CATEGORY.getCategory());

        assertThat(result).isEqualTo(UPDATED_CATEGORY.getCategory());
    }

    @Test
    public void should_delete_existing_organization(){
        when(service.delete(SAVED_CATEGORY.getCategory().getId())).thenReturn(true);

        ResponseEntity result = controller.delete(SAVED_CATEGORY.getCategory());

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(service).delete(SAVED_CATEGORY.getCategory().getId());
    }

    @Test
    public void should_return_404_when_try_to_delete_not_existing_organization(){
        when(service.delete(SAVED_ORGANIZATION.getOrganization().getId())).thenReturn(false);

        ResponseEntity result = controller.delete(SAVED_CATEGORY.getCategory());

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(service).delete(SAVED_CATEGORY.getCategory().getId());
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