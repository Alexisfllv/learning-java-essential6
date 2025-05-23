package edu.com.javaesencial07salesapi.repo;


import edu.com.javaesencial07salesapi.dto.sale.Venta;
import edu.com.javaesencial07salesapi.dto.sale.Venta_DTO;
import edu.com.javaesencial07salesapi.entity.Sale;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepo extends GenericRepo<Sale,Long> {

    // LLAMADA DE VIEW select * from sale;
    @Query(value = "select * from fn_sales", nativeQuery = true)
    List<Object[]> listadoVentas();

    @Query(value = "select * from fn_sales", nativeQuery = true)
    List<Venta> listadoVentas2();

    // procedure para convertir todo a true en sale
    @Procedure(procedureName = "all_true")
    void convertirTodasLasVentas();

}
