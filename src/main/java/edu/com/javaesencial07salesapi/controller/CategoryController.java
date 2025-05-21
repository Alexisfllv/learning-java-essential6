package edu.com.javaesencial07salesapi.controller;


import edu.com.javaesencial07salesapi.dto.category.CategoryDTO;
import edu.com.javaesencial07salesapi.dto.category.Category_DTO;
import edu.com.javaesencial07salesapi.dto.category.Category_RDTO;
import edu.com.javaesencial07salesapi.entity.Category;
import edu.com.javaesencial07salesapi.mapper.CategoryMapper;
import edu.com.javaesencial07salesapi.service.CategoryService;
import edu.com.javaesencial07salesapi.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    //
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<Category_DTO>> listAllCategory(){
        List<Category_DTO> lists = mapperUtil.mapList(categoryService.listAll(), Category_DTO.class,"categoryMapper");
        return  ResponseEntity.status(HttpStatus.OK).body(lists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category_DTO> findById(@PathVariable Long id){
        Category obj = categoryService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapperUtil.map(obj, Category_DTO.class,"categoryMapper"));
    }

    @PostMapping
    public ResponseEntity<Category_DTO> save(@Valid @RequestBody Category_DTO dto){
        Category obj = categoryService.save(mapperUtil.map(dto, Category.class,"categoryMapper"));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapperUtil.map(obj, Category_DTO.class,"categoryMapper"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category_DTO> update(@Valid @RequestBody Category_DTO dto, @PathVariable Long id){
        Category obj = categoryService.update(mapperUtil.map(dto, Category.class,"categoryMapper"),id);
        return ResponseEntity.status(HttpStatus.OK).body(mapperUtil.map(obj, Category_DTO.class,"categoryMapper"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        categoryService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    // busqueda por nombre query derivada
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Category_DTO>> searchByName(@PathVariable("name") String name){
        List<Category_DTO> lists = mapperUtil.mapList(categoryService.findByCategoryName(name), Category_DTO.class,"categoryMapper");
        return ResponseEntity.status(HttpStatus.OK).body(lists);
    }

    // busqueda para textos en la descripcion
    @GetMapping("/description/{texto}")
    public ResponseEntity<List<Category_DTO>> searchByDescription(@PathVariable("texto") String texto){
        List<Category_DTO> lists = mapperUtil.mapList(categoryService.findByCategoryDescriptionLike(texto), Category_DTO.class,"categoryMapper");
        return ResponseEntity.status(HttpStatus.OK).body(lists);
    }

    // buscada de nombre y descripcion con jpq
    @GetMapping("/busquedajqp")
    public ResponseEntity<List<Category_DTO>> searchByNameAndDescription(@RequestParam("name") String name, @RequestParam("description") String description){
        List<Category_DTO> lists = mapperUtil.mapList(categoryService.getNameAndDescription(name,description), Category_DTO.class,"categoryMapper");
        return ResponseEntity.status(HttpStatus.OK).body(lists);
    }



    // conversion de parametros  entiy,dto .  dto,entity

//    private Category_DTO convertTODto(Category category){
//        return modelMapper.map(category, Category_DTO.class);
//    }
//
//    private Category convertEntity(Category_DTO dto){
//        return modelMapper.map(dto, Category.class);
//    }


}
