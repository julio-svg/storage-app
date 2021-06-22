package com.project.storage.storagems.service;

import com.project.storage.commons.dto.Product;
import com.project.storage.storagems.controller.rdto.request.ProductREQDTO;

import java.util.List;


public interface ProductService {

    List<Product> getAllProducts(List<String> id, List<String>nombre, List<String>precio);
    Product getProduct(String id);
    Product createProduct(ProductREQDTO productREQDTO);
    void modifyProduct(ProductREQDTO productREQDTO, String id);
    void deleteProduct(String id);


}
