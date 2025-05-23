package edu.com.javaesencial07salesapi.service;

import edu.com.javaesencial07salesapi.dto.sale.Venta;
import edu.com.javaesencial07salesapi.dto.sale.Venta_DTO;
import edu.com.javaesencial07salesapi.entity.Sale;

import java.util.List;

public interface SaleService extends GenericService<Sale,Long> {

    // listado
    List<Venta_DTO> Ventas();

    List<Venta> Ventas2();

    // metodo todo a true
    void convertirSale();

}
