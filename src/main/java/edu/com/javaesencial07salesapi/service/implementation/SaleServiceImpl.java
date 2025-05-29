package edu.com.javaesencial07salesapi.service.implementation;


import edu.com.javaesencial07salesapi.dto.sale.*;
import edu.com.javaesencial07salesapi.entity.Sale;
import edu.com.javaesencial07salesapi.exception.ModelNotFoundException;
import edu.com.javaesencial07salesapi.repo.SaleRepo;
import edu.com.javaesencial07salesapi.repo.GenericRepo;
import edu.com.javaesencial07salesapi.service.SaleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
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

    // 01

    @Override
    public Sale getSaleWithMoreCost() {
        return saleRepo.findAll()
                .stream()
                .max(Comparator.comparing(Sale::getSaleTotal))
                .orElseThrow(() -> new ModelNotFoundException("No hay ventas registradas"));
    }

    @Override
    public MaxVendorUser_DTO getBestSellerman() {
        return saleRepo.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        sale -> sale.getUser().getUserName(), // usar el nombre del usuario
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                Sale::getSaleTotal,
                                BigDecimal::add
                        )
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue()) // mayor total
                .map(entry -> new MaxVendorUser_DTO(entry.getKey(), entry.getValue()))
                .orElse(null);
    }

    @Override
    public MaxCountVendorUser_DTO getBestSellermanWithMaxCount() {
        return saleRepo.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        sale -> sale.getUser().getUserName(),
                        Collectors.collectingAndThen(
                                Collectors.counting(),
                                Long::intValue
                        )
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> new MaxCountVendorUser_DTO(entry.getKey(), entry.getValue()))
                .orElse(null);
    }

    @Override
    public SellerStatsDTO getSellerStats() {
        return saleRepo.findAll()
                .stream()
                // Agrupar por usuario y crear DTO con total y cantidad
                .collect(Collectors.groupingBy(
                        sale -> sale.getUser().getUserName(),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    BigDecimal totalSales = list.stream()
                                            .map(Sale::getSaleTotal)
                                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                                    int countSales = list.size();

                                    return new SellerStatsDTO(list.get(0).getUser().getUserName(), totalSales, countSales);
                                }
                        )
                ))
                .values()
                .stream()
                // Obtener el que tiene el máximo totalSales
                .max(Comparator.comparing(SellerStatsDTO::totalSales))
                // Por si no hay ventas, devolver algo por defecto o null
                .orElse(null);
    }


}
