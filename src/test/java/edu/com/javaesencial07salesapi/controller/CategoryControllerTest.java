package edu.com.javaesencial07salesapi.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.com.javaesencial07salesapi.dto.category.Category_DTO;
import edu.com.javaesencial07salesapi.entity.Category;
import edu.com.javaesencial07salesapi.service.CategoryService;
import edu.com.javaesencial07salesapi.util.MapperUtil;
import lombok.extern.slf4j.Slf4j;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {


    @Autowired
    private MockMvc mockMvc;

    // Mocks
    @MockitoBean
    private CategoryService categoryService;

    @MockitoBean
    private MapperUtil mapperUtil;

    @Autowired
    private ObjectMapper objectMapper;

    // ✅ Extraemos valores comunes a atributos reutilizables
    private Category category1;
    private Category category2;
    private Category category3;

    private Category_DTO dto1;
    private Category_DTO dto2;
    private Category_DTO dto3;
    private Category_DTO sindto;

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

    @Test
    // para crear una category prueba
    void shouldCreateCategorySuccessfully() throws Exception {

        //
        // Crear un DTO sin ID para simular POST
        Category_DTO dtoSinId = new Category_DTO( null,"Ropa",
                "Ropa y accesorios para hombres, mujeres y niños.", false);
        // DTO =  ENTITY
        Mockito.when(mapperUtil.map(dtoSinId, Category.class, "categoryMapper")).thenReturn(category3);
        // save entity
        Mockito.when(categoryService.save(category3)).thenReturn(category3);
        // ENTITY = DTO
        Mockito.when(mapperUtil.map(category3, Category_DTO.class, "categoryMapper")).thenReturn(dto3);

        mockMvc.perform(MockMvcRequestBuilders
                .post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dtoSinId))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idCategory").value(3L))
                .andExpect(jsonPath("$.nombreCortoCategory").value("Ropa"))
                .andExpect(jsonPath("$.categoryDescription").value("Ropa y accesorios para hombres, mujeres y niños."))
                .andExpect(jsonPath("$.categoryEnabled").value(false));

        // verify
        verify(categoryService, times(1)).save(category3);
        verify(mapperUtil).map(dtoSinId, Category.class, "categoryMapper");
        verify(mapperUtil).map(category3, Category_DTO.class, "categoryMapper");
    }

    // test para modificar
    @Test
    void shouldUpdateCategorySuccessfully() throws Exception {
        final Long id = 3L;

        Category categoryMod = new Category(3L, "Mineral", "Minerales", true);
        // dto salida
        Category_DTO dtoEntrada = new Category_DTO(3L, "Mineral", "Minerales", true);

        Category_DTO dtosalida = new Category_DTO( 3L,"Mineral", "Minerales", true);

        // Mocks
        Mockito.when(mapperUtil.map(dtoEntrada, Category.class, "categoryMapper")).thenReturn(categoryMod);
        Mockito.when(categoryService.update(categoryMod, id)).thenReturn(categoryMod);
        Mockito.when(mapperUtil.map(categoryMod, Category_DTO.class, "categoryMapper")).thenReturn(dtosalida);

        mockMvc.perform(MockMvcRequestBuilders
                .put(BASE_URL + "/" + id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dtoEntrada))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCategory").value(3L))
                .andExpect(jsonPath("$.nombreCortoCategory").value("Mineral"))
                .andExpect(jsonPath("$.categoryDescription").value("Minerales"))
                .andExpect(jsonPath("$.categoryEnabled").value(true));

        // Verificaciones (verify)
        Mockito.verify(mapperUtil).map(dtoEntrada, Category.class, "categoryMapper");
        Mockito.verify(categoryService).update(categoryMod, id);
        Mockito.verify(mapperUtil).map(categoryMod, Category_DTO.class, "categoryMapper");

    }






}
