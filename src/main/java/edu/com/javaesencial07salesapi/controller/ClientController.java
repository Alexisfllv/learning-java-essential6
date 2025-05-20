package edu.com.javaesencial07salesapi.controller;



import edu.com.javaesencial07salesapi.dto.client.Client_DTO;
import edu.com.javaesencial07salesapi.entity.Client;
import edu.com.javaesencial07salesapi.service.ClientService;
import edu.com.javaesencial07salesapi.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    //
    private final MapperUtil mapperUtil;
    @GetMapping
    public ResponseEntity<List<Client_DTO>> listAllClient(){
        List<Client_DTO> lists = mapperUtil.mapList(clientService.listAll(), Client_DTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(lists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client_DTO> findById(@PathVariable Long id){
        Client obj = clientService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapperUtil.map(obj, Client_DTO.class));
    }

    @PostMapping
    public ResponseEntity<Client_DTO> save(@Valid @RequestBody Client_DTO dto){
        Client obj = clientService.save(mapperUtil.map(dto, Client.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapperUtil.map(obj, Client_DTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client_DTO> update(@Valid @RequestBody Client_DTO dto, @PathVariable Long id){
        Client obj = clientService.update(mapperUtil.map(dto,Client.class),id);
        return ResponseEntity.status(HttpStatus.OK).body(mapperUtil.map(obj, Client_DTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        clientService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




}