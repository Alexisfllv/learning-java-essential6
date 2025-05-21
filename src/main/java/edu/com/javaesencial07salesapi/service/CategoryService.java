package edu.com.javaesencial07salesapi.service;

import edu.com.javaesencial07salesapi.dto.category.CategoryDTO;
import edu.com.javaesencial07salesapi.entity.Category;

import java.util.List;

public interface CategoryService extends GenericService<Category,Long> {


    // listado derivado
    List<Category> findByCategoryName(String name);

    // listado de palabras clave
    List<Category> findByCategoryDescriptionLike(String texto);


    // JPQL Querys
    List<Category> getNameAndDescription(String name, String desc);


}
