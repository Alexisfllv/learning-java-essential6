package edu.com.javaesencial07salesapi.repo;

import edu.com.javaesencial07salesapi.entity.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDetailRepo extends JpaRepository<SaleDetail,Long> {
}
