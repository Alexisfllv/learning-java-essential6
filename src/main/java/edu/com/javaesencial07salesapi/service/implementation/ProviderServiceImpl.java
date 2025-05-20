package edu.com.javaesencial07salesapi.service.implementation;


import edu.com.javaesencial07salesapi.entity.Provider;
import edu.com.javaesencial07salesapi.repo.ProviderRepo;
import edu.com.javaesencial07salesapi.repo.GenericRepo;
import edu.com.javaesencial07salesapi.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProviderServiceImpl extends CRUDIMPL<Provider,Long> implements ProviderService {

    private final ProviderRepo providerRepo;


    @Override
    protected GenericRepo<Provider, Long> getRepo() {
        return providerRepo;
    }



}
