package com.project.storage.product.controller.rdto.response;

import com.project.storage.commons.dto.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductAllRSPDTO {

    List<Product> data;

    public ProductAllRSPDTO() {
        data = new ArrayList<>();
    }

    public ProductAllRSPDTO addData (Product product){
        data.add(product);
        return this;
    }
}
