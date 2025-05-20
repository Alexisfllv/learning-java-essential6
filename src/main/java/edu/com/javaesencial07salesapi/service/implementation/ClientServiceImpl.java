package edu.com.javaesencial07salesapi.service.implementation;


import edu.com.javaesencial07salesapi.entity.Client;

import edu.com.javaesencial07salesapi.repo.ClientRepo;
import edu.com.javaesencial07salesapi.repo.GenericRepo;
import edu.com.javaesencial07salesapi.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl extends CRUDIMPL<Client,Long> implements ClientService {

    private final ClientRepo clientRepo;


    @Override
    protected GenericRepo<Client, Long> getRepo() {
        return clientRepo;
    }



}
