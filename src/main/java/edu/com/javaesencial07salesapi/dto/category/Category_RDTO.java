package edu.com.javaesencial07salesapi.dto.category;

public record Category_RDTO(
        Long idCategory,
        String categoryName,
        String categoryDescription,
        boolean categoryEnabled
) {
}
