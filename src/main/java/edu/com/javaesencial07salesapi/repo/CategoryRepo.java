package edu.com.javaesencial07salesapi.repo;


import edu.com.javaesencial07salesapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends GenericRepo<Category,Long> {

    // DerivedQueries Querys derivadas
    // Select * from category c where c.name = '';
    List<Category> findByCategoryName(String name );

    // select * from category c where c.name like '%texto%'
    // texto => '%para%'
    List<Category> findByCategoryDescriptionLike(String texto);

    // JPQL

    @Query("from Category c where c.categoryName = :name and c.categoryDescription like %:desc%")
    List<Category> getNameAndDescription(@Param("name") String name, @Param("desc") String desc);




}
