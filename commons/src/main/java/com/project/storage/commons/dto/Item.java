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
    Double total;

    public Item(List<Product> productList, Integer quantity) {
        this.productList = productList;
        this.quantity = quantity;
        this.calculateTotal();
    }

    public Item() {
        productList = new ArrayList<>();
    }

    private void calculateTotal() {
        productList.stream()
                .map(Product::getPrecio)
                .forEach(a -> this.total = a * quantity);
    }

    public Item addProduct(Product product) {
        productList.add(product);
        return this;
    }

}
