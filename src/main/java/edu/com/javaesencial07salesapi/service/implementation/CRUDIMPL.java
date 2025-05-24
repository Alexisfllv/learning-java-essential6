package edu.com.javaesencial07salesapi.service.implementation;

import edu.com.javaesencial07salesapi.exception.ModelNotFoundException;
import edu.com.javaesencial07salesapi.repo.GenericRepo;
import edu.com.javaesencial07salesapi.service.GenericService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
        return getRepo().findById(id)
                .orElseThrow(() -> new ModelNotFoundException("ITEM NO ENCONTRADO : "+id));
    }

    @Override
    public T save(T t) {
        return getRepo().save(t);
    }

    @Override
    public T update(T t, ID id) {
        try {
            Class<?> clazz = t.getClass();
            String methodName = "setId" + clazz.getSimpleName();
            Method setIdMethod = clazz.getMethod(methodName, id.getClass());
            setIdMethod.invoke(t, id);

            getRepo().findById(id)
                    .orElseThrow(() -> new ModelNotFoundException("ITEM NO ENCONTRADO: " + id));
            return getRepo().save(t);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Error al usar reflexión en update()", e);
        }
    }


    @Override
    public void deleteById(ID id) {
        getRepo().findById(id)
                .orElseThrow(() -> new ModelNotFoundException("ITEM NO ENCONTRADO : "+id));
        getRepo().deleteById(id);
    }
}
