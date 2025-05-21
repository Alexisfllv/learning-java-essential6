package edu.com.javaesencial07salesapi.dto.sale_detail;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import edu.com.javaesencial07salesapi.dto.product.Product_DTO;
import edu.com.javaesencial07salesapi.dto.sale.Sale_DTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleDetail_DTO {

    @NotNull
    private short quantity;

    @NotNull
    private double salePrice;

    @NotNull
    private double discount;

    // fks
    //@NotNull
    @JsonBackReference
    private Sale_DTO sale;

    @NotNull
    @JsonIncludeProperties({"idProduct"})
    private Product_DTO product;
}
