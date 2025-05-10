package edu.com.javaesencial07salesapi.service;

import edu.com.javaesencial07salesapi.dto.category.CategoryDTO;

import java.util.List;

public interface GenericService<T,ID> {
    // CRUD
    List<T> listAll();
    T findById(ID id);
    T save (T t);
    T update(T t , ID id);
    void deleteById(ID id);
}
