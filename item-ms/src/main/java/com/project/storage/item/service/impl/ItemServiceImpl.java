package com.project.storage.item.service.impl;

import com.project.storage.commons.dto.Item;
import com.project.storage.commons.dto.Product;
import com.project.storage.commons.dto.ms.product.rdto.response.ProductAllRSPDTO;
import com.project.storage.commons.exceptions.ClienteException;
import com.project.storage.commons.exceptions.ServerException;
import com.project.storage.item.service.ItemService;
import com.project.storage.item.service.validation.ItemValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ItemValidation itemValidation;

    private static final String CTE_QUANTITY = "Quantity";
    private static final String CTE_ID = "ID";


    @Override
    public Item getAllProduct() {
        ResponseEntity<ProductAllRSPDTO> responseEntity = callProduct();
        List<Product> listProducts = null;
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            listProducts = responseEntity.getBody().getData();
        }
        return new Item(listProducts, 0);
    }

    @Override
    public Item getProductWithQuantity(String ident, String quantity) {
        itemValidation.numericIsMandatory(ident, CTE_ID);
        itemValidation.numericIsMandatory(quantity, CTE_QUANTITY);
        List<Product> listProducts = null;

        ResponseEntity<ProductAllRSPDTO> responseEntity = callProduct(ident);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            listProducts = responseEntity.getBody().getData();
        }
        return new Item(listProducts, Integer.valueOf(quantity));
    }

    protected ResponseEntity<ProductAllRSPDTO> callProduct() {
        return callProduct(null);
    }

    protected ResponseEntity<ProductAllRSPDTO> callProduct(String param) {

        final String URL = "http://localhost:8080/v1/products";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(URL);
        if (param != null) {
            uriBuilder.queryParam("ids", param);
        }
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity<ProductAllRSPDTO> requestEntity = new HttpEntity<>(requestHeaders);
        ResponseEntity<ProductAllRSPDTO> responseEntity = null;

        try {
            responseEntity = restTemplate.exchange(
                    uriBuilder.toUriString(),
                    HttpMethod.GET,
                    requestEntity,
                    ProductAllRSPDTO.class);
        } catch (HttpClientErrorException e) {
            throw new ClienteException(e.getMessage());
        } catch (HttpServerErrorException e) {
            throw new ServerException(e.getMessage());
        }
        return responseEntity;
    }
}
