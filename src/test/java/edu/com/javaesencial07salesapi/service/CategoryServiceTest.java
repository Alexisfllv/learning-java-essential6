package edu.com.javaesencial07salesapi.service;

import edu.com.javaesencial07salesapi.entity.Category;
import edu.com.javaesencial07salesapi.exception.ModelNotFoundException;
import edu.com.javaesencial07salesapi.repo.CategoryRepo;
import edu.com.javaesencial07salesapi.service.implementation.CategoryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@Slf4j
@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {

    //
    @Mock
    private CategoryRepo categoryRepo;
    @InjectMocks
    private CategoryServiceImpl categoryService;


    // ✅ Extraemos valores comunes a atributos reutilizables
    private Category category1;
    private Category category2;
    private Category category3;

    // valores preestablecidos
    @BeforeEach
    void setUp() {
        // ✅ Inicialización común a todos los tests
        category1 = new Category(1L, "Tecnología",
                "Productos relacionados con computadoras, teléfonos y otros dispositivos electrónicos.", true);

        category2 = new Category(2L, "Hogar",
                "Artículos para el hogar como muebles, decoración y electrodomésticos.", true);

        category3 = new Category(3L, "Ropa",
                "Ropa y accesorios para hombres, mujeres y niños.", false);

    }

    // test de listar
    @Test
    void givenCategoriesExist_whenListAll_thenReturnsAllCategories() throws Exception {

        List<Category> categories = Arrays.asList(category1, category2, category3);
        when(categoryRepo.findAll()).thenReturn(categories);

        List<Category> res = categoryService.listAll();
        log.info("Lista de categorias: {}", res);

        assertNotNull(res);
        assertFalse(res.isEmpty());
        assertEquals(res.size(),3);

        verify(categoryRepo).findAll();

    }
    // test de busqueda
    @Test
    void givenCategoryId_whenFindById_thenReturnsCategory() throws Exception {
        Long ID = 1L;
        List<Category> categories = Arrays.asList(category1, category2, category3);

        when(categoryRepo.findById(ID)).thenReturn(Optional.of(category1));

        Category cat = categoryService.findById(ID);

        assertNotNull(cat);
        assertEquals(category1.getIdCategory(), cat.getIdCategory());
        assertEquals(category1.getCategoryName(), cat.getCategoryName());
        assertEquals(category1.getCategoryDescription(), cat.getCategoryDescription());
        assertEquals(category1.isCategoryEnabled(), cat.isCategoryEnabled());
        verify(categoryRepo).findById(ID);
    }

    @Test
    void givenInvalidId_whenFindById_thenThrowsException() {
        Long invalidId = 99L;
        when(categoryRepo.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(ModelNotFoundException.class, () -> {
            categoryService.findById(invalidId);
        });

        verify(categoryRepo).findById(invalidId);
    }

    // test de guardar category
    @Test
    void givenValidCategory_whenSave_thenReturnsSavedCategory() throws Exception {

        Category catsave = new Category(null, "Tecnología",
                "Productos relacionados con computadoras, teléfonos y otros dispositivos electrónicos.", true);

        when(categoryRepo.save(catsave)).thenReturn(category1);

        Category res = categoryService.save(catsave);

        assertNotNull(res);
        assertEquals(category1.getIdCategory(), res.getIdCategory());
        assertEquals(category1.getCategoryName(), res.getCategoryName());
        assertEquals(category1.getCategoryDescription(), res.getCategoryDescription());
        assertEquals(category1.isCategoryEnabled(), res.isCategoryEnabled());

        verify(categoryRepo).save(catsave);
    }

    // test de modificar
    @Test
    void givenValidCategory_whenUpdate_thenReturnsUpdatedCategory() throws Exception {

        Long ID = 1L;
        Category newCat = new Category(1L, "Mineral",
                "Minerales", false);

        when(categoryRepo.findById(ID)).thenReturn(Optional.of(category1));
        when(categoryRepo.save(newCat)).thenReturn(newCat);
        Category res = categoryService.update(newCat, ID);

        log.info(res.toString());
        assertNotNull(res);
        assertEquals(newCat.getIdCategory(), res.getIdCategory());
        assertEquals(newCat.getCategoryName(), res.getCategoryName());
        assertEquals(newCat.getCategoryDescription(), res.getCategoryDescription());
        assertEquals(newCat.isCategoryEnabled(), res.isCategoryEnabled());

        verify(categoryRepo).findById(ID);
        verify(categoryRepo).save(newCat);

    }

    //test de eliminar
    @Test
    void givenValidCategory_whenDelete_thenReturnsDeletedCategory() throws Exception {
        Long ID = 1L;
        when(categoryRepo.findById(ID)).thenReturn(Optional.of(category1));
        when(categoryRepo.existsById(ID)).thenReturn(false);

        categoryService.deleteById(ID);

        verify(categoryRepo).findById(ID);
        verify(categoryRepo).deleteById(ID);
        verify(categoryRepo,times(1)).deleteById(ID);

        assertFalse(categoryRepo.existsById(ID));
    }
}
