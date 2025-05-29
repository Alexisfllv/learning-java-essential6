package edu.com.javaesencial07salesapi.controller;



import edu.com.javaesencial07salesapi.dto.sale.*;
import edu.com.javaesencial07salesapi.entity.Sale;
import edu.com.javaesencial07salesapi.service.SaleService;
import edu.com.javaesencial07salesapi.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sale")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;
    //
    private final MapperUtil mapperUtil;
    @GetMapping
    public ResponseEntity<List<Sale_DTO>> listAllSale(){
        List<Sale_DTO> lists = mapperUtil.mapList(saleService.listAll(), Sale_DTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(lists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale_DTO> findById(@PathVariable Long id){
        Sale obj = saleService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapperUtil.map(obj, Sale_DTO.class));
    }

    @PostMapping
    public ResponseEntity<Sale_DTO> save(@Valid @RequestBody Sale_DTO dto){
        Sale obj = saleService.save(mapperUtil.map(dto, Sale.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapperUtil.map(obj, Sale_DTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sale_DTO> update(@Valid @RequestBody Sale_DTO dto, @PathVariable Long id){
        Sale obj = saleService.update(mapperUtil.map(dto,Sale.class),id);
        return ResponseEntity.status(HttpStatus.OK).body(mapperUtil.map(obj, Sale_DTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        saleService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/ventas")
    public ResponseEntity<List<Venta_DTO>> listadoVentas(){
        List<Venta_DTO> ventas =  saleService.Ventas();
        return ResponseEntity.status(HttpStatus.OK).body(ventas);
    }

    @GetMapping("/ventas2")
    public ResponseEntity<List<Venta>> listadoVentas2(){
        List<Venta> ventas =  saleService.Ventas2();
        return ResponseEntity.status(HttpStatus.OK).body(ventas);
    }

    @PostMapping("/alltrue")
    public ResponseEntity<Void> allTrue(){
        saleService.convertirSale();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // maximo
    @GetMapping("/max")
    public ResponseEntity<Sale_DTO> max(){
        Sale_DTO venta = mapperUtil.map(saleService.getSaleWithMoreCost(), Sale_DTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(venta);
    }

    @GetMapping("/maxVendedorUser")
    public ResponseEntity<MaxVendorUser_DTO> maxVendedorUser(){
        MaxVendorUser_DTO venta = saleService.getBestSellerman();
        return ResponseEntity.status(HttpStatus.OK).body(venta);
    }

    @GetMapping("/maxCountUser")
    public ResponseEntity<MaxCountVendorUser_DTO> maxCountUser(){
        MaxCountVendorUser_DTO venta = saleService.getBestSellermanWithMaxCount();
        return ResponseEntity.status(HttpStatus.OK).body(venta);
    }

    @GetMapping("/maxSeller")
    public ResponseEntity<SellerStatsDTO> maxSeller(){
        SellerStatsDTO venta = saleService.getSellerStats();
        return ResponseEntity.status(HttpStatus.OK).body(venta);
    }



}