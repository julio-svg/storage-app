package com.project.storage.storagems.repository;

import com.project.storage.commons.dto.models.ProductDTO;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductDTO, Long> {
}
