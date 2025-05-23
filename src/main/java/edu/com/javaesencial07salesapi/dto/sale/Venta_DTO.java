package edu.com.javaesencial07salesapi.dto.sale;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta_DTO {

    private Integer cantidadVenta;
    private LocalDate fechaVenta;
}
