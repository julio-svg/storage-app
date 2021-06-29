package com.project.storage.commons.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Item {

    List<Product> productList;
    Integer quantity;

    public Item() {
        productList = new ArrayList<>();
    }

    public Double calculateTotal(){
        return productList.stream()
                .map(Product::getPrecio)
                .reduce((a,b) -> a + b).orElse(0D);
    }

    public Item addProduct(Product product){
        productList.add(product);
        return this;
    }

}
