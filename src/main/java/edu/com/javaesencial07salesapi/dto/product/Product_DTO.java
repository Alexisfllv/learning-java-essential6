package edu.com.javaesencial07salesapi.dto.product;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product_DTO {

    private Long idProduct;

    @NotNull
    private String productName;

    @NotNull
    @Min(value = 1)
    private BigDecimal productPrice;

    @NotNull
    private String productDescription;

    @NotNull
    private boolean productEnabled;


    @NotNull
    @Min(value = 1)
    private Long idCategory;
}
