package com.project.storage.item.service;

import com.project.storage.commons.dto.Item;



public interface ItemService {
    Item getAllProduct();
    Item getProductWithQuantity(String id,String quantity);
}
