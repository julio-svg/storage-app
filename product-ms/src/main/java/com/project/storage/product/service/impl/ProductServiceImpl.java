package com.project.storage.product.service.impl;

import com.project.storage.commons.dto.Product;
import com.project.storage.commons.dto.models.ProductDTO;
import com.project.storage.commons.dto.ms.product.rdto.request.ProductREQDTO;
import com.project.storage.product.repository.ProductRepository;
import com.project.storage.product.service.ProductService;
import com.project.storage.product.service.mapper.ProductMapper;
import com.project.storage.product.service.validator.ProductValidator;
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

    private static final String CTE_ID = "Id";
    private static final String CTE_PRECIO = "Precio";

    @Override
    public List<Product> getAllProducts(List<String> ids, List<String> nombre, List<String> precio) {

        validator.numericIsMandatory(ids, CTE_ID);
        validator.numericIsMandatory(precio, CTE_PRECIO);

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
        validator.numericIsMandatory(id,CTE_ID);
        return mapper.toProduct(productRepository.findById(Long.parseLong(id)).orElse(new ProductDTO()));
    }

    @Override
    public Product createProduct(ProductREQDTO productREQDTO) {
        ProductDTO productDTO = new ProductDTO();
        validator.numericIsMandatory(productREQDTO.getPrecio(),"Precio");
        productDTO.setPrecio(Double.valueOf(productREQDTO.getPrecio()));
        productDTO.setNombre(productREQDTO.getNombre());
        return mapper.toProduct(productRepository.save(productDTO));

    }

    @Override
    public void modifyProduct(ProductREQDTO productREQDTO, String id) {
        validator.numericIsMandatory(id,CTE_ID);
        ProductDTO productDTO = productRepository.findById(Long.parseLong(id)).orElse(new ProductDTO());
        if(productDTO != null) {
            validator.numericIsMandatory(productREQDTO.getPrecio(),"Precio");
            productDTO.setPrecio(Double.valueOf(productREQDTO.getPrecio()));
            productDTO.setNombre(productREQDTO.getNombre());
            productRepository.save(productDTO);
        }

    }

    @Override
    public void deleteProduct(String id) {
        validator.numericIsMandatory(id,CTE_ID);
        productRepository.delete(new ProductDTO(Long.parseLong(id)));
    }

    private boolean filter(Long field, List<String> ids) {
        return ids == null ||
                ids.parallelStream()
                        .map(Long::valueOf)
                        .map(idDouble -> 0 == idDouble.compareTo(field))
                        .reduce((a, b) -> a || b)
                        .orElse(true);
    }

    private boolean filter(String field, List<String> nombre) {
        return nombre == null ||
                nombre.parallelStream()
                        .map(id -> field.toLowerCase().contains(id.toLowerCase()))
                        .reduce((a, b) -> a || b)
                        .orElse(true);
    }

    private boolean filter(Double field, List<String> precio) {
        return precio == null ||
                precio.parallelStream()
                        .filter(Objects::nonNull)
                        .filter(a -> ! a.isEmpty())
                        .map(Double::valueOf)
                        .map(idDouble -> 0 == idDouble.compareTo(field))
                        .reduce((a, b) -> a || b)
                        .orElse(true);
    }


}
