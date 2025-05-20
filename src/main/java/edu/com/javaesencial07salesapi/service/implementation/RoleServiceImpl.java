package edu.com.javaesencial07salesapi.service.implementation;


import edu.com.javaesencial07salesapi.entity.Role;
import edu.com.javaesencial07salesapi.repo.RoleRepo;
import edu.com.javaesencial07salesapi.repo.GenericRepo;
import edu.com.javaesencial07salesapi.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl extends CRUDIMPL<Role,Long> implements RoleService {

    private final RoleRepo roleRepo;


    @Override
    protected GenericRepo<Role, Long> getRepo() {
        return roleRepo;
    }



}
