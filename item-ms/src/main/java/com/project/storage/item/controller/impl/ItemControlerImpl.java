package com.project.storage.item.controller.impl;

import com.project.storage.item.controller.rdto.response.ItemAllRSPDTO;
import com.project.storage.item.controller.rdto.response.ItemRSPDTO;
import com.project.storage.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class ItemControlerImpl {

    @Autowired
    ItemService itemService;

    @GetMapping("items/products/{id}")
    public ResponseEntity<ItemRSPDTO> getItemProduct(@PathVariable() String id, @RequestParam() String quantity){
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("items/products")
    public ResponseEntity<ItemAllRSPDTO> getAllItems(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}