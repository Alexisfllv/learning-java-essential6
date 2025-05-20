package edu.com.javaesencial07salesapi.repo;


import edu.com.javaesencial07salesapi.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends GenericRepo<User,Long> {
}
