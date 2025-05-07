package edu.com.javaesencial07salesapi.service;

import edu.com.javaesencial07salesapi.dto.category.CategoryDTO;
import edu.com.javaesencial07salesapi.entity.Category;

import java.util.List;

public interface CategoryService {

    // CRUD
    List<CategoryDTO> listAllCategory();
    CategoryDTO findById(Long id);
    CategoryDTO save (CategoryDTO categoryDTO);
    CategoryDTO update(CategoryDTO categoryDTO , Long id);
    void deleteById(Long id);

}
