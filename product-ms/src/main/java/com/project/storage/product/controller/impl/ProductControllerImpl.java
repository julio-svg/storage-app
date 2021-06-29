package com.project.storage.product.controller.impl;

import com.project.storage.commons.dto.Product;
import com.project.storage.product.controller.rdto.request.ProductREQDTO;
import com.project.storage.product.controller.rdto.response.ProductAllRSPDTO;
import com.project.storage.product.controller.rdto.response.ProductRSPDTO;
import com.project.storage.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController()
@RequestMapping("/v1")
public class ProductControllerImpl {

    Logger logger = LoggerFactory.getLogger(ProductControllerImpl.class);

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    ResponseEntity<ProductAllRSPDTO> getAllProducts(
             @RequestParam(required = false, name = "ids") List<String> id,
             @RequestParam(required = false, name = "nombres") List<String> nombre,
             @RequestParam(required = false, name = "precios") List<String> precio) {
        logger.info("Product´s list");

        ProductAllRSPDTO allProducts = new ProductAllRSPDTO();
        List<Product> listProducts = productService.getAllProducts(id, nombre, precio);
        HttpStatus httpStatus = null;

        if (listProducts.size() != 0) {
            httpStatus = HttpStatus.OK;
            allProducts.setData(listProducts);
        } else {
            httpStatus = HttpStatus.NO_CONTENT;
            allProducts.setData(null);
        }
        return new ResponseEntity(allProducts, httpStatus);
    }


    @GetMapping("/products/{id}")
    ResponseEntity<ProductRSPDTO> getProduct(@PathVariable String id) {
        logger.info("Product detail");

        Product product = productService.getProduct(id);
        ProductRSPDTO productRSPDTO = new ProductRSPDTO();
        HttpStatus httpStatus = null;

        if (product != null) {
            productRSPDTO.setData(product);
            httpStatus = HttpStatus.OK;
        } else {
            productRSPDTO.setData(product);
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity(product, httpStatus);
    }

    @PostMapping("/products")
    ResponseEntity<Void> createProduct(@RequestBody ProductREQDTO productREQDTO) {
        logger.info("Create product");

        MultiValueMap<String, String> headers = new LinkedMultiValueMap();
        headers.add("Location", "/products/" + String.valueOf(productService.createProduct(productREQDTO).getId()));
        ResponseEntity<Void> response = new ResponseEntity(headers, HttpStatus.CREATED);
        return response;

    }

    @PutMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> modifyProduct(@RequestBody ProductREQDTO productREQDTO, @PathVariable String id) {
        logger.info("Modify product");

        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        productService.modifyProduct(productREQDTO, id);
        return responseEntity;

    }

    @DeleteMapping("/products/{id}")
    ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        logger.info("Delete product");

        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        productService.deleteProduct(id);
        return responseEntity;

    }

}
