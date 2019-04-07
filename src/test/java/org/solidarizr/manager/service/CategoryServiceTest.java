package org.solidarizr.manager.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.solidarizr.manager.model.Category;
import org.solidarizr.manager.repository.CategoryRepository;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        Category categoryToSave = Category.builder()
                .name("Category 1").build();

        Category categorySaved = Category.builder()
                .id(1)
                .name("Category 1")
                .build();

        when(repository.save(categoryToSave)).thenReturn(categorySaved);

        Category result = service.save(categoryToSave);

        assertThat(result).isEqualTo(categorySaved);
    }

    @Test
    public void should_update_category(){
        Category categoryToSave = Category.builder()
                .id(1)
                .name("Category 1 Modified").build();

        Category categorySaved = Category.builder()
                .id(1)
                .name("Category 1 Modified")
                .build();

        when(repository.save(categorySaved)).thenReturn(categorySaved);

        Category result = service.save(categoryToSave);

        assertThat(result).isEqualTo(categorySaved);
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
    public void should_delete_category(){
        Category categoryToDelete = Category.builder()
                .id(1)
                .name("CAtegory 1").build();

        service.delete(categoryToDelete);

        verify(repository).delete(categoryToDelete);
    }
}