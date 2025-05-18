package edu.com.javaesencial07salesapi.config;

import edu.com.javaesencial07salesapi.dto.category.Category_DTO;
import edu.com.javaesencial07salesapi.dto.category.Category_RDTO;
import edu.com.javaesencial07salesapi.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {


    @Bean("defaultMapper")
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean("categoryMapper")
    public ModelMapper categoryMapper() {
        ModelMapper mapper = new ModelMapper();

        // handle missmatches
        // lectura
        mapper.createTypeMap(Category.class, Category_DTO.class)
                .addMapping(e ->e.getCategoryName(),((destination, value) -> destination.setNombreCortoCategory((String) value)));

        // escritura
        mapper.createTypeMap(Category_DTO.class, Category.class)
                .addMapping(e ->e.getNombreCortoCategory(),((destination, value) -> destination.setCategoryName((String) value)));


        return mapper;
    }
}
