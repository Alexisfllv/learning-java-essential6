package edu.com.javaesencial07salesapi.service.implementation;


import edu.com.javaesencial07salesapi.dto.sale.Venta;
import edu.com.javaesencial07salesapi.dto.sale.Venta_DTO;
import edu.com.javaesencial07salesapi.entity.Sale;
import edu.com.javaesencial07salesapi.repo.SaleRepo;
import edu.com.javaesencial07salesapi.repo.GenericRepo;
import edu.com.javaesencial07salesapi.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SaleServiceImpl extends CRUDIMPL<Sale,Long> implements SaleService {

    private final SaleRepo saleRepo;


    @Override
    protected GenericRepo<Sale, Long> getRepo() {
        return saleRepo;
    }


    @Override
    public List<Venta_DTO> Ventas() {
        return saleRepo.listadoVentas().stream()
                .map(e -> new Venta_DTO(
                        Integer.parseInt(String.valueOf(e[0])),
                        LocalDate.parse(String.valueOf(e[1]), DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<Venta> Ventas2() {
        return saleRepo.listadoVentas2();
    }

    @Transactional
    @Override
    public void convertirSale() {
        saleRepo.convertirTodasLasVentas();
    }

}
