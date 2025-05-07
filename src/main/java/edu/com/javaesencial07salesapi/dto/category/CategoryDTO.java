package edu.com.javaesencial07salesapi.dto.category;

public record CategoryDTO(
   Long idCategory,
   String categoryName,
   String categoryDescription,
   boolean categoryEnabled
) {}
