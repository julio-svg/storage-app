package com.project.storage.item.service.impl;

import com.project.storage.commons.dto.Item;
import com.project.storage.commons.dto.Product;
import com.project.storage.commons.dto.ms.product.rdto.response.ProductAllRSPDTO;
import com.project.storage.item.service.validation.ItemValidation;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {

    @InjectMocks
    ItemServiceImpl itemService;

    @Mock
    RestTemplate restTemplate;

    @Mock
    ItemValidation itemValidation;

    public static ResponseEntity<ProductAllRSPDTO> responseEntity;
    public static ResponseEntity<ProductAllRSPDTO> responseEntityQuantity;

    @BeforeAll
    public static void generarObjetos(){
        EasyRandom generator = new EasyRandom();
        List<Product> productList = generator.objects(Product.class,9).collect(Collectors.toList());
        ProductAllRSPDTO productAllRSPDTO = new ProductAllRSPDTO();
        productAllRSPDTO.setData(productList);
        responseEntity = new ResponseEntity(productAllRSPDTO, HttpStatus.OK);

        List<Product> product = generator.objects(Product.class,1).collect(Collectors.toList());
        product.get(0).setPrecio(1000D);
        ProductAllRSPDTO productAllRSPDTOQuantity = new ProductAllRSPDTO();
        productAllRSPDTOQuantity.setData(product);
        responseEntityQuantity = new ResponseEntity(productAllRSPDTOQuantity, HttpStatus.OK);

    }

    @Test
    public void getAllProductTest(){
        when(restTemplate.exchange(anyString(),any(HttpMethod.class),any(HttpEntity.class),any(Class.class))).thenReturn(responseEntity);
        assertAll(() -> assertEquals(9 , itemService.getAllProduct().getProductList().size()));

    }

    @Test
    public void getProductWithQuantityTest(){

        String id = String.valueOf(responseEntityQuantity.getBody().getData().get(0).getId());
        when(restTemplate.exchange(anyString(),any(HttpMethod.class),any(HttpEntity.class),any(Class.class))).thenReturn(responseEntityQuantity);
        Item response = itemService.getProductWithQuantity(id,"3");
        assertAll(
                () -> assertEquals(1 , response.getProductList().size()),
                () -> assertEquals(3000D , response.getTotal()),
                () -> assertEquals(3 , response.getQuantity())
        );

    }

}