package edu.com.javaesencial07salesapi.repo;

import edu.com.javaesencial07salesapi.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepo extends GenericRepo<Product,Long> {

    // jpql
    @Query("from Product p where p.category.categoryName = :name")
    List<Product> getProductsByCategory(String name);

    // native query
    @Query(nativeQuery = true, value = "SELECT * FROM product")
    List<Product> listadodeProductos();

    @Query(value = "SELECT * FROM product WHERE product_description LIKE :desc", nativeQuery = true)
    List<Product> buscarPorDescripcion(@Param("desc") String desc);



}
