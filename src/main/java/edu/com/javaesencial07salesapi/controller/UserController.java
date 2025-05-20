package edu.com.javaesencial07salesapi.controller;



import edu.com.javaesencial07salesapi.dto.user.User_DTO;
import edu.com.javaesencial07salesapi.entity.User;
import edu.com.javaesencial07salesapi.service.UserService;
import edu.com.javaesencial07salesapi.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    //
    private final MapperUtil mapperUtil;
    @GetMapping
    public ResponseEntity<List<User_DTO>> listAllUser(){
        List<User_DTO> lists = mapperUtil.mapList(userService.listAll(), User_DTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(lists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User_DTO> findById(@PathVariable Long id){
        User obj = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapperUtil.map(obj, User_DTO.class));
    }

    @PostMapping
    public ResponseEntity<User_DTO> save(@Valid @RequestBody User_DTO dto){
        User obj = userService.save(mapperUtil.map(dto, User.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapperUtil.map(obj, User_DTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User_DTO> update(@Valid @RequestBody User_DTO dto, @PathVariable Long id){
        User obj = userService.update(mapperUtil.map(dto,User.class),id);
        return ResponseEntity.status(HttpStatus.OK).body(mapperUtil.map(obj, User_DTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




}