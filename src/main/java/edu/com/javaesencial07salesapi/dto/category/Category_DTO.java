package edu.com.javaesencial07salesapi.dto.category;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category_DTO {
    private Long idCategory;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 50)
    private String categoryName;

    @NotNull
    @Size(min = 3, max = 250)
    private String categoryDescription;

    @NotNull
    private boolean categoryEnabled;
}
