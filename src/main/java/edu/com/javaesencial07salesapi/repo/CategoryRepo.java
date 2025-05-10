package edu.com.javaesencial07salesapi.repo;


import edu.com.javaesencial07salesapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends GenericRepo<Category,Long> {
}
