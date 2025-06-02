package edu.com.javaesencial07salesapi.controller;


import edu.com.javaesencial07salesapi.dto.category.Category_DTO;
import edu.com.javaesencial07salesapi.entity.Category;
import edu.com.javaesencial07salesapi.service.CategoryService;
import edu.com.javaesencial07salesapi.util.MapperUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

// statics
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.modelmapper.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {


    @Autowired
    private MockMvc mockMvc;

    // Mocks
    @MockitoBean
    private CategoryService categoryService;

    @MockitoBean
    private MapperUtil mapperUtil;

    // ✅ Extraemos valores comunes a atributos reutilizables
    private Category category1;
    private Category category2;
    private Category category3;

    private Category_DTO dto1;
    private Category_DTO dto2;
    private Category_DTO dto3;

    private static final String BASE_URL = "/api/category"; // ✅ Ruta base reutilizable


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

        dto1 = new Category_DTO(1L, "Tecnología",
                "Productos relacionados con computadoras, teléfonos y otros dispositivos electrónicos.", true);

        dto2 = new Category_DTO(2L, "Hogar",
                "Artículos para el hogar como muebles, decoración y electrodomésticos.", true);

        dto3 = new Category_DTO(3L, "Ropa",
                "Ropa y accesorios para hombres, mujeres y niños.", false);
    }

    @Test
    void shouldReturnAllCategoriesSuccessfully() throws Exception {
        // ✅ Nombres descriptivos ayudan a saber qué se prueba
        List<Category> list = Arrays.asList(category1, category2, category3);
        List<Category_DTO> dtoList = Arrays.asList(dto1, dto2, dto3);

        Mockito.when(categoryService.listAll()).thenReturn(list);
        Mockito.when(mapperUtil.mapList(list, Category_DTO.class, "categoryMapper")).thenReturn(dtoList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(3)));
    }

    @Test
    void shouldReturnCategoryByIdSuccessfully() throws Exception {
        final Long id = 1L;

        Mockito.when(categoryService.findById(id)).thenReturn(category1);
        Mockito.when(mapperUtil.map(category1, Category_DTO.class, "categoryMapper")).thenReturn(dto1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(BASE_URL + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].idCategory").value(1L))
                .andExpect(jsonPath("$.data[0].nombreCortoCategory").value("Tecnología"))
                .andExpect(jsonPath("$.data[0].categoryDescription").value("Productos relacionados con computadoras, teléfonos y otros dispositivos electrónicos."))
                .andExpect(jsonPath("$.data[0].categoryEnabled").value(true));
    }


}
