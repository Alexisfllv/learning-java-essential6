package edu.com.javaesencial07salesapi.service.implementation;

import edu.com.javaesencial07salesapi.entity.Product;
import edu.com.javaesencial07salesapi.repo.ProductRepo;
import edu.com.javaesencial07salesapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    //ioc
    private final ProductRepo productRepo;

    @Override
    public List<Product> ListProduct() {
        return productRepo.findAll();
    }


}
