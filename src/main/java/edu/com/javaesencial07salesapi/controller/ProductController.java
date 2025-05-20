package edu.com.javaesencial07salesapi.controller;



import edu.com.javaesencial07salesapi.dto.product.Product_DTO;
import edu.com.javaesencial07salesapi.entity.Product;
import edu.com.javaesencial07salesapi.service.ProductService;
import edu.com.javaesencial07salesapi.util.MapperUtil;
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
    private final MapperUtil mapperUtil;
    @GetMapping
    public ResponseEntity<List<Product_DTO>> listAllProduct(){
        List<Product_DTO> lists = mapperUtil.mapList(productService.listAll(), Product_DTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(lists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product_DTO> findById(@PathVariable Long id){
        Product obj = productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapperUtil.map(obj, Product_DTO.class));
    }

    @PostMapping
    public ResponseEntity<Product_DTO> save(@Valid @RequestBody Product_DTO dto){
        Product obj = productService.save(mapperUtil.map(dto, Product.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapperUtil.map(obj, Product_DTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product_DTO> update(@Valid @RequestBody Product_DTO dto, @PathVariable Long id){
        Product obj = productService.update(mapperUtil.map(dto,Product.class),id);
        return ResponseEntity.status(HttpStatus.OK).body(mapperUtil.map(obj, Product_DTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        productService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // conversion de parametros  entiy,dto .  dto,entity
//
//    private Product_DTO convertTODto(Product product){
//        return modelMapper.map(product, Product_DTO.class);
//    }
//
//    private Product convertEntity(Product_DTO dto){
//        return modelMapper.map(dto, Product.class);
//    }


}