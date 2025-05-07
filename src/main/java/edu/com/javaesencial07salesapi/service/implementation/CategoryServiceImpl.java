package edu.com.javaesencial07salesapi.service.implementation;


import edu.com.javaesencial07salesapi.dto.category.CategoryDTO;
import edu.com.javaesencial07salesapi.entity.Category;
import edu.com.javaesencial07salesapi.mapper.CategoryMapper;
import edu.com.javaesencial07salesapi.repo.CategoryRepo;
import edu.com.javaesencial07salesapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> listAllCategory() {
       List<Category> categoryList = categoryRepo.findAll();

       return categoryList.stream()
               .map(category -> categoryMapper.toCategoryDTO(category))
               .toList();
    }

    @Override
    public CategoryDTO findById(Long id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("dont exists id : "+id));

        return categoryMapper.toCategoryDTO(category);
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category =  categoryMapper.toCategory(categoryDTO);
        category = categoryRepo.save(category);
        return categoryMapper.toCategoryDTO(category);
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO, Long id) {
        Category categoryExist = categoryRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("dont exists id : "+id));

        categoryExist.setCategoryName(categoryDTO.categoryName());
        categoryExist.setCategoryDescription(categoryDTO.categoryDescription());
        categoryExist.setCategoryEnabled(categoryDTO.categoryEnabled());
        categoryRepo.save(categoryExist);
        return categoryMapper.toCategoryDTO(categoryExist);
    }

    @Override
    public void deleteById(Long id) {
        Category categoryExist = categoryRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("dont exists id : "+id));

        categoryRepo.delete(categoryExist);
    }
}
