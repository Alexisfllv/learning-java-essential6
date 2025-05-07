package edu.com.javaesencial07salesapi.mapper;


import edu.com.javaesencial07salesapi.dto.category.CategoryDTO;
import edu.com.javaesencial07salesapi.entity.Category;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface CategoryMapper {

    Category toCategory(CategoryDTO categoryDTO);

    CategoryDTO toCategoryDTO(Category category);
}
