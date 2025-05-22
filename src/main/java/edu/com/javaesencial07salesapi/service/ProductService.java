package edu.com.javaesencial07salesapi.service;


import edu.com.javaesencial07salesapi.entity.Product;

import java.util.List;

public interface ProductService extends GenericService<Product,Long> {

    // buscar producto por nombre de categorias
    List<Product> getProductsByCategory(String name);

    // listado
    List<Product> listadodeProductos();

    // buscar por descripcion
    List<Product> buscarPorDescripcion(String desc);



}
