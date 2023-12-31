package com.application.rest.controller;

import com.application.rest.controller.dto.MakerDTO;
import com.application.rest.entities.Maker;
import com.application.rest.service.IMakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/maker")
public class MakerController {

    @Autowired
    private IMakerService makerService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Maker> makerOptional = makerService.findById(id);

        if (makerOptional.isPresent()){
            Maker maker = makerOptional.get();
            MakerDTO makerDTO = MakerDTO.builder()
                    .id(maker.getId())
                    .name(maker.getName())
                    .productList(maker.getProductList())
                    .build();
            return ResponseEntity.ok(makerDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<MakerDTO> makerList = makerService
                .findAll()
                .stream()
                .map(maker -> MakerDTO.builder()
                        .id(maker.getId())
                        .name(maker.getName())
                        .productList(maker.getProductList())
                        .build())
                .toList();

        return ResponseEntity.ok(makerList);
    }


}
