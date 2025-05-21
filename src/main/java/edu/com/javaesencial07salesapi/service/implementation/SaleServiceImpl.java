package edu.com.javaesencial07salesapi.service.implementation;


import edu.com.javaesencial07salesapi.entity.Sale;
import edu.com.javaesencial07salesapi.repo.SaleRepo;
import edu.com.javaesencial07salesapi.repo.GenericRepo;
import edu.com.javaesencial07salesapi.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SaleServiceImpl extends CRUDIMPL<Sale,Long> implements SaleService {

    private final SaleRepo clientRepo;


    @Override
    protected GenericRepo<Sale, Long> getRepo() {
        return clientRepo;
    }



}
