package edu.com.javaesencial07salesapi.repo;


import edu.com.javaesencial07salesapi.entity.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends GenericRepo<Client,Long> {
}
