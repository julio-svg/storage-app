package com.project.storage.storagems.service.impl;

import com.project.storage.commons.dto.Product;
import com.project.storage.commons.dto.models.ProductDTO;
import com.project.storage.storagems.controller.rdto.request.ProductREQDTO;
import com.project.storage.storagems.repository.ProductRepository;
import com.project.storage.storagems.service.ProductService;
import com.project.storage.storagems.service.mapper.ProductMapper;
import com.project.storage.storagems.service.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper mapper;

    @Autowired
    ProductValidator validator;

    @Override
    public List<Product> getAllProducts(List<String> ids, List<String> nombre, List<String> precio) {

        validator.numericIsMandatory(ids,"Id");
        validator.numericIsMandatory(precio,"Precio");

        List<ProductDTO> lista = (List<ProductDTO>) productRepository.findAll();
        List<Product> result = lista.stream()
                .map(a -> mapper.toProduct(a))
                .filter(productDTO -> filter(productDTO.getId(), ids))
                .filter(productDTO -> filter(productDTO.getNombre(), nombre))
                .filter(productDTO -> filter(productDTO.getPrecio(), precio))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public Product getProduct(String id) {
        validator.numericIsMandatory(id,"Id");
        return mapper.toProduct(productRepository.findById(Long.parseLong(id)).orElse(null));
    }

    @Override
    public Product createProduct(ProductREQDTO productREQDTO) {
        ProductDTO productDTO = new ProductDTO();
        validator.numericIsMandatory(productREQDTO.getPrecio(),"Precioo");
        productDTO.setPrecio(Double.valueOf(productREQDTO.getPrecio()));
        productDTO.setNombre(productREQDTO.getNombre());
        return mapper.toProduct(productRepository.save(productDTO));

    }

    @Override
    public void modifyProduct(ProductREQDTO productREQDTO, String id) {
        validator.numericIsMandatory(id,"Id");
        ProductDTO productDTO = productRepository.findById(Long.parseLong(id)).orElse(null);
        if(productDTO != null) {
            validator.numericIsMandatory(productREQDTO.getPrecio(),"Precioo");
            productDTO.setPrecio(Double.valueOf(productREQDTO.getPrecio()));
            productDTO.setNombre(productREQDTO.getNombre());
            productRepository.save(productDTO);
        }

    }

    @Override
    public void deleteProduct(String id) {
        productRepository.delete(new ProductDTO(Long.parseLong(id)));
    }

    private boolean filter(Long field, List<String> ids) {

        boolean result = ids == null ||
                ids.parallelStream()
                        .map(Long::valueOf)
                        .map(idDouble -> 0 == idDouble.compareTo(field))
                        .reduce((a, b) -> a || b)
                        .orElse(true);

        return result;
    }

    private boolean filter(String field, List<String> nombre) {
        boolean result = nombre == null ||
                nombre.parallelStream()
                        .map(id -> field.toLowerCase().contains(id.toLowerCase()))
                        .reduce((a, b) -> a || b)
                        .orElse(true);
        return result;
    }

    private boolean filter(Double field, List<String> precio) {
        boolean result = precio == null ||
                precio.parallelStream()
                        .filter(Objects::nonNull)
                        .filter(a -> ! a.isEmpty())
                        .map(Double::valueOf)
                        .map(idDouble -> 0 == idDouble.compareTo(field))
                        .reduce((a, b) -> a || b)
                        .orElse(true);

        return result;
    }


}
