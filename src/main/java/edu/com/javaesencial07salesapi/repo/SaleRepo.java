package edu.com.javaesencial07salesapi.repo;


import edu.com.javaesencial07salesapi.entity.Sale;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepo extends GenericRepo<Sale,Long> {
}
