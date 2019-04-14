package org.solidarizr.manager.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.solidarizr.manager.model.Category;
import org.solidarizr.manager.repository.CategoryRepository;
import org.solidarizr.manager.util.CategoryFixture;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.solidarizr.manager.util.CategoryFixture.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {
    @Mock
    CategoryRepository repository;

    CategoryService service;

    @Before
    public void setUp(){
        service = new CategoryService(repository);
    }

    @Test
    public void should_insert_category(){
        when(repository.save(NOT_INSERTED_CATEGORY.getCategory())).thenReturn(SAVED_CATEGORY.getCategory());

        Category result = service.save(NOT_INSERTED_CATEGORY.getCategory());

        assertThat(result).isEqualTo(SAVED_CATEGORY.getCategory());
    }

    @Test
    public void should_update_category(){
        when(repository.save(TO_UPDATE_CATEGORY.getCategory())).thenReturn(UPDATED_CATEGORY.getCategory());

        Category result = service.save(TO_UPDATE_CATEGORY.getCategory());

        assertThat(result).isEqualTo(UPDATED_CATEGORY.getCategory());
    }

    @Test
    public void should_return_all_categories(){
        List<Category> allCategories = Arrays.asList(
                Category.builder()
                        .id(1)
                        .name("Category 1").build(),
                Category.builder()
                        .id(1)
                        .name("Category 2").build());

        when(repository.findAll()).thenReturn(allCategories);

        List<Category> result = service.getAll();

        assertThat(result).containsExactlyInAnyOrderElementsOf(allCategories);
    }

    @Test
    public void should_delete_category_when_it_exists(){
        when(repository.findById(1)).thenReturn(Optional.of(SAVED_CATEGORY.getCategory()));
        Boolean deleted = service.delete(SAVED_CATEGORY.getCategory().getId());

        verify(repository).delete(SAVED_CATEGORY.getCategory());
        assertThat(deleted).isTrue();
    }

    @Test
    public void should_not_delete_category_when_it_doesnt_exists(){
        when(repository.findById(1)).thenReturn(Optional.empty());
        Boolean deleted = service.delete(SAVED_CATEGORY.getCategory().getId());

        verify(repository, never()).delete(SAVED_CATEGORY.getCategory());
        assertThat(deleted).isFalse();
    }
}