package com.project.storage.storagems.service.mapper;

import com.project.storage.commons.dto.models.ProductDTO;
import com.project.storage.commons.dto.Product;
import com.project.storage.storagems.controller.rdto.request.ProductREQDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductDTO p);
    ProductDTO toProductDTO(ProductREQDTO p);
}
