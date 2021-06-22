package com.project.storage.storagems.controller.impl;

import com.project.storage.commons.dto.Product;
import com.project.storage.storagems.controller.rdto.request.ProductREQDTO;
import com.project.storage.storagems.controller.rdto.response.ProductAllRSPDTO;
import com.project.storage.storagems.controller.rdto.response.ProductRSPDTO;
import com.project.storage.storagems.exceptions.ProductException;
import com.project.storage.storagems.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController()
@RequestMapping("/v1")
public class ProductControllerImpl {

    Logger logger = LoggerFactory.getLogger(ProductControllerImpl.class);

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    ResponseEntity<ProductAllRSPDTO> getAllProducts(
            @Valid() @RequestParam(required = false, name = "ids") List<String> id,
            @Valid() @RequestParam(required = false, name = "nombres") List<String> nombre,
            @Valid() @RequestParam(required = false, name = "precios") List<String> precio) {
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
        try {
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
        } catch (Exception e) {
            throw new ProductException();
        }

    }

    @PostMapping("/products")
    ResponseEntity<Void> createProduct(@RequestBody ProductREQDTO productREQDTO) {
        logger.info("Create product");
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("localtion", String.valueOf(productService.createProduct(productREQDTO).getId()));
            ResponseEntity<Void> response = new ResponseEntity(headers, HttpStatus.CREATED);
            return response;
        } catch (Exception e) {
            throw new ProductException();
        }
    }

    @PutMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> modifyProduct(@RequestBody ProductREQDTO productREQDTO, @PathVariable String id) {
        logger.info("Modify product");
        try {
            ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            productService.modifyProduct(productREQDTO, id);
            return responseEntity;
        } catch (Exception e) {
            throw new ProductException();
        }
    }

    @DeleteMapping("/products/{id}")
    ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        logger.info("Delete product");
        try {
            ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            productService.deleteProduct(id);
            return responseEntity;
        } catch (Exception e) {
            throw new ProductException();
        }
    }

}
