package edu.com.javaesencial07salesapi.service.implementation;


import edu.com.javaesencial07salesapi.dto.category.CategoryDTO;
import edu.com.javaesencial07salesapi.entity.Category;
import edu.com.javaesencial07salesapi.mapper.CategoryMapper;
import edu.com.javaesencial07salesapi.repo.CategoryRepo;
import edu.com.javaesencial07salesapi.repo.GenericRepo;
import edu.com.javaesencial07salesapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl extends CRUDIMPL<Category,Long> implements CategoryService {

    private final CategoryRepo categoryRepo;


    @Override
    protected GenericRepo<Category, Long> getRepo() {
        return categoryRepo;
    }


    @Override
    public List<Category> findByCategoryName(String name) {
        return categoryRepo.findByCategoryName(name);
    }

    @Override
    public List<Category> findByCategoryDescriptionLike(String texto) {
        return categoryRepo.findByCategoryDescriptionLike("%" + texto + "%");    }

    @Override
    public List<Category> getNameAndDescription(String name, String desc) {
        return categoryRepo.getNameAndDescription(name, desc);
    }

    @Override
    public Page<Category> findPage(Pageable pageable) {
        return categoryRepo.findAll(pageable);
    }

    @Override
    public List<Category> listadodeCategorys(String param) {

        Sort.Direction direction = param.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;

        return categoryRepo.findAll(Sort.by(direction,"categoryName"));
    }
}
