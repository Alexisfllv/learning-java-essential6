package edu.com.javaesencial07salesapi.dto.sale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaxVendorUser_DTO {
    String nameUser;
    BigDecimal totalSales;
}
