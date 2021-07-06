package com.project.storage.item.controller.impl;

import com.project.storage.commons.dto.Item;
import com.project.storage.commons.dto.ms.item.rdto.response.ItemRSPDTO;
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
        Item item = itemService.getProductWithQuantity(id,quantity);
        HttpStatus httpStatus;
        ItemRSPDTO itemRSPDTO = new ItemRSPDTO();
        itemRSPDTO.setData(item);
        if(item != null){
            httpStatus = HttpStatus.OK;
        }else{
            httpStatus = HttpStatus.NO_CONTENT;
        }

        return  new ResponseEntity<>(itemRSPDTO,httpStatus);
    }

    @GetMapping("items/products")
    public ResponseEntity<ItemRSPDTO> getAllItems(){
        Item item = itemService.getAllProduct();
        ItemRSPDTO itemRSPDTO = new ItemRSPDTO();
        itemRSPDTO.setData(item);
        HttpStatus httpStatus;
        if(item != null){
            httpStatus = HttpStatus.OK;
        }else{
            httpStatus = HttpStatus.NO_CONTENT;
        }
        return  new ResponseEntity<>(itemRSPDTO,httpStatus);
    }

}
