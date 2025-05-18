package edu.com.javaesencial07salesapi.controller;


import edu.com.javaesencial07salesapi.dto.category.CategoryDTO;
import edu.com.javaesencial07salesapi.dto.category.Category_DTO;
import edu.com.javaesencial07salesapi.dto.category.Category_RDTO;
import edu.com.javaesencial07salesapi.entity.Category;
import edu.com.javaesencial07salesapi.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<Category_DTO>> listAllCategory(){
        List<Category_DTO> categoryList = categoryService.listAll()
                .stream()                                // clase == ClaseDto
                .map(this::convertTODto)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(categoryList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category_DTO> findById(@PathVariable Long id){
        Category obj = categoryService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(convertTODto(obj));
    }

    @PostMapping
    public ResponseEntity<Category_DTO> save(@Valid @RequestBody Category_DTO dto){
        Category obj = categoryService.save(convertEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(convertTODto(obj));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category_DTO> update(@Valid @RequestBody Category_DTO dto, @PathVariable Long id){
        Category obj = categoryService.update(convertEntity(dto),id);
        return ResponseEntity.status(HttpStatus.OK).body(convertTODto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        categoryService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // conversion de parametros  entiy,dto .  dto,entity

    private Category_DTO convertTODto(Category category){
        return modelMapper.map(category, Category_DTO.class);
    }

    private Category convertEntity(Category_DTO dto){
        return modelMapper.map(dto, Category.class);
    }


}
