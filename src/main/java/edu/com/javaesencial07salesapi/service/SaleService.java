package edu.com.javaesencial07salesapi.service;

import edu.com.javaesencial07salesapi.dto.sale.*;
import edu.com.javaesencial07salesapi.dto.sale_detail.ProductSalesDTO;
import edu.com.javaesencial07salesapi.entity.Sale;

import java.util.List;

public interface SaleService extends GenericService<Sale,Long> {

    // listado
    List<Venta_DTO> Ventas();

    List<Venta> Ventas2();

    // metodo todo a true
    void convertirSale();

    // Querys complejas

    // Obtener la venta mas costosa
    Sale getSaleWithMoreCost();

    // Obtener el mejor vendedor
    MaxVendorUser_DTO getBestSellerman();

    // Obtener el mejor con mejor cantidad de ventas
    MaxCountVendorUser_DTO getBestSellermanWithMaxCount();
    // mejor vendedor , total y cantidad
    SellerStatsDTO getSellerStats();

    // Producto mayor vendido - sale detail

    List<ProductSalesDTO> getProductSalesSummary();

}
