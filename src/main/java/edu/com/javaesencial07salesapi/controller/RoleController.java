package edu.com.javaesencial07salesapi.controller;



import edu.com.javaesencial07salesapi.dto.role.Role_DTO;
import edu.com.javaesencial07salesapi.entity.Role;
import edu.com.javaesencial07salesapi.service.RoleService;
import edu.com.javaesencial07salesapi.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
    //
    private final MapperUtil mapperUtil;
    @GetMapping
    public ResponseEntity<List<Role_DTO>> listAllRole(){
        List<Role_DTO> lists = mapperUtil.mapList(roleService.listAll(), Role_DTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(lists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role_DTO> findById(@PathVariable Long id){
        Role obj = roleService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapperUtil.map(obj, Role_DTO.class));
    }

    @PostMapping
    public ResponseEntity<Role_DTO> save(@Valid @RequestBody Role_DTO dto){
        Role obj = roleService.save(mapperUtil.map(dto, Role.class));

        log.warn("Role saved : {}", obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapperUtil.map(obj, Role_DTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role_DTO> update(@Valid @RequestBody Role_DTO dto, @PathVariable Long id){
        Role obj = roleService.update(mapperUtil.map(dto,Role.class),id);
        return ResponseEntity.status(HttpStatus.OK).body(mapperUtil.map(obj, Role_DTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        roleService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




}