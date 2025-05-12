package edu.com.javaesencial07salesapi.dto.category;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category_DTO {
    private Long idCategory;
    private String categoryName;
    private String categoryDescription;
    private boolean categoryEnabled;
}
