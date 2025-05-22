package edu.com.javaesencial07salesapi.service.implementation;

import edu.com.javaesencial07salesapi.entity.Product;
import edu.com.javaesencial07salesapi.repo.GenericRepo;
import edu.com.javaesencial07salesapi.repo.ProductRepo;
import edu.com.javaesencial07salesapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends CRUDIMPL<Product,Long> implements ProductService {

    //ioc
    private final ProductRepo productRepo;


    @Override
    protected GenericRepo<Product, Long> getRepo() {
        return productRepo;
    }

    @Override
    public List<Product> getProductsByCategory(String name) {
        return productRepo.getProductsByCategory(name);
    }

    @Override
    public List<Product> listadodeProductos() {
        return productRepo.listadodeProductos();
    }

    @Override
    public List<Product> buscarPorDescripcion(String desc) {
        return productRepo.buscarPorDescripcion("%" + desc + "%");
    }
}
