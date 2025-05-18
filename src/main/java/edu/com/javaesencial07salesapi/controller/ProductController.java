package edu.com.javaesencial07salesapi.controller;



import edu.com.javaesencial07salesapi.dto.product.Product_DTO;
import edu.com.javaesencial07salesapi.entity.Product;
import edu.com.javaesencial07salesapi.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    //
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<Product_DTO>> listAllProduct(){
        List<Product_DTO> lists = productService.listAll()
                .stream()                                // clase == ClaseDto
                .map(this::convertTODto)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(lists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product_DTO> findById(@PathVariable Long id){
        Product obj = productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(convertTODto(obj));
    }

    @PostMapping
    public ResponseEntity<Product_DTO> save(@Valid @RequestBody Product_DTO dto){
        Product obj = productService.save(convertEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(convertTODto(obj));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product_DTO> update(@Valid @RequestBody Product_DTO dto, @PathVariable Long id){
        Product obj = productService.update(convertEntity(dto),id);
        return ResponseEntity.status(HttpStatus.OK).body(convertTODto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        productService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // conversion de parametros  entiy,dto .  dto,entity

    private Product_DTO convertTODto(Product product){
        return modelMapper.map(product, Product_DTO.class);
    }

    private Product convertEntity(Product_DTO dto){
        return modelMapper.map(dto, Product.class);
    }


}