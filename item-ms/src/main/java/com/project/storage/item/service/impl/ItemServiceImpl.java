package com.project.storage.item.service.impl;

import com.project.storage.commons.dto.Item;
import com.project.storage.commons.dto.ms.product.rdto.response.ProductAllRSPDTO;
import com.project.storage.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    RestTemplate restTemplate;

    private String url = "http://localhost:8080/v1/products";

    @Override
    public Item getAllProduct() {
        ResponseEntity<ProductAllRSPDTO> responseEntity = callProduct();
        return new Item(responseEntity.getBody().getData(), 0);
    }

    @Override
    public Item getProductWithQuantity(String ident,String quantity) {
        ResponseEntity<ProductAllRSPDTO> responseEntity = callProduct(ident);
        return new Item(responseEntity.getBody().getData(),Integer.valueOf(quantity));
    }

    private ResponseEntity<ProductAllRSPDTO> callProduct(){
        return callProduct(null);
    }

    private ResponseEntity<ProductAllRSPDTO> callProduct(String param){

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
        if(param != null) {
            uriBuilder.queryParam("ids", param);
        }
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity<ProductAllRSPDTO> requestEntity = new HttpEntity<>(requestHeaders);
        return restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                requestEntity,
                ProductAllRSPDTO.class);
    }
}
