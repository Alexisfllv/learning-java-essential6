package edu.com.javaesencial07salesapi.controller;



import edu.com.javaesencial07salesapi.dto.provider.Provider_DTO;
import edu.com.javaesencial07salesapi.entity.Provider;
import edu.com.javaesencial07salesapi.service.ProviderService;
import edu.com.javaesencial07salesapi.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provider")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService providerService;
    //
    private final MapperUtil mapperUtil;
    @GetMapping
    public ResponseEntity<List<Provider_DTO>> listAllProvider(){
        List<Provider_DTO> lists = mapperUtil.mapList(providerService.listAll(), Provider_DTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(lists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Provider_DTO> findById(@PathVariable Long id){
        Provider obj = providerService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapperUtil.map(obj, Provider_DTO.class));
    }

    @PostMapping
    public ResponseEntity<Provider_DTO> save(@Valid @RequestBody Provider_DTO dto){
        Provider obj = providerService.save(mapperUtil.map(dto, Provider.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapperUtil.map(obj, Provider_DTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Provider_DTO> update(@Valid @RequestBody Provider_DTO dto, @PathVariable Long id){
        Provider obj = providerService.update(mapperUtil.map(dto,Provider.class),id);
        return ResponseEntity.status(HttpStatus.OK).body(mapperUtil.map(obj, Provider_DTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        providerService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




}