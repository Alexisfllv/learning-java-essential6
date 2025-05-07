package edu.com.javaesencial07salesapi.controller;


import edu.com.javaesencial07salesapi.entity.Product;
import edu.com.javaesencial07salesapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping
    public ResponseEntity<List<Product>> listAllProductos(){
        return ResponseEntity.ok(productService.ListProduct());
    }
}
