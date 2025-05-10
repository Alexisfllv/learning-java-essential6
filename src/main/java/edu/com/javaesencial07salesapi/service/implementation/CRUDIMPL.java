package edu.com.javaesencial07salesapi.service.implementation;

import edu.com.javaesencial07salesapi.repo.GenericRepo;
import edu.com.javaesencial07salesapi.service.GenericService;

import java.util.List;

public abstract class CRUDIMPL<T,ID> implements GenericService<T,ID> {

    // ioc
    protected abstract GenericRepo<T,ID> getRepo();


    @Override
    public List<T> listAll() {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) {
        return getRepo().findById(id).orElse(null);
    }

    @Override
    public T save(T t) {
        return getRepo().save(t);
    }

    @Override
    public T update(T t, ID id) {
        return getRepo().save(t);
    }

    @Override
    public void deleteById(ID id) {
        getRepo().deleteById(id);
    }
}
