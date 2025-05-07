package edu.com.javaesencial07salesapi.repo;

import edu.com.javaesencial07salesapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
}
