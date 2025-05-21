package edu.com.javaesencial07salesapi.dto.sale;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.com.javaesencial07salesapi.dto.client.Client_DTO;
import edu.com.javaesencial07salesapi.dto.sale_detail.SaleDetail_DTO;
import edu.com.javaesencial07salesapi.dto.user.User_DTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Sale_DTO {

    private Long idSale;

    @NotNull
    private LocalDateTime  saleDateTime;

    @NotNull
    private Double saleTotal;

    @NotNull
    private Double saleTax;

    @NotNull
    private boolean saleEnabled;

    // fks

    @NotNull
    private Client_DTO client;

    @NotNull
    @JsonIncludeProperties({"idUser"})
    private User_DTO user;

    // list
    @JsonManagedReference
    private List<SaleDetail_DTO> details;
}
