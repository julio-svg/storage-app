package com.project.storage.product.service.impl;

import com.project.storage.commons.dto.Product;
import com.project.storage.commons.dto.models.ProductDTO;
import com.project.storage.commons.dto.ms.product.rdto.request.ProductREQDTO;
import com.project.storage.product.repository.ProductRepository;

import com.project.storage.product.service.mapper.ProductMapper;
import com.project.storage.product.service.validator.ProductValidator;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Test ProductService")
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductValidator validator;

    @Mock
    ProductRepository productRepository;

    @Mock
    ProductMapper productMapper;

    private static List<ProductDTO> productDTOList;
    private static Optional<ProductDTO> optionalProductDTO;
    private static Product product;
    private static ProductREQDTO productREQDTO;
    private static ProductDTO productDTO;
    String id = "7";

    private static List<String> ids;

    @BeforeAll
    public static void generarObjetos() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        parameters.stringLengthRange(5, 10);
        EasyRandom generator = new EasyRandom(parameters);

        product = generator.nextObject(Product.class);
        productDTOList= generarProductDTOList(generator);
        optionalProductDTO = generator.objects(ProductDTO.class, 1).findFirst();
        productREQDTO = generator.nextObject(ProductREQDTO.class);
        productREQDTO.setPrecio(String.valueOf((int) Math.floor(Math.random() * (0 - 1000 + 1) + 0)));
        productDTO = generator.nextObject(ProductDTO.class);
        productDTO.setPrecio((Double) Math.floor(Math.random() * (0 - 1000 + 1) + 0));
    }

    @Test
    @DisplayName("getAllProductsTest")
    public void getAllProductsTest() {
        when(productMapper.toProduct(any(ProductDTO.class))).thenReturn(product);
        doNothing().when(validator).numericIsMandatory(new ArrayList<>(), "Id");
        when(productRepository.findAll()).thenReturn(productDTOList);

        assertAll(() -> assertEquals(9, (productService.getAllProducts(new ArrayList<>(), new ArrayList<>(), new ArrayList<>())).size()),
                () -> assertEquals(9, (productService.getAllProducts(ids, new ArrayList<>(), new ArrayList<>())).size()));
    }

    @Test
    public void getProductTest() {
        id = String.valueOf(product.getId());

        doNothing().when(validator).numericIsMandatory(id, "Id");
        when(productMapper.toProduct(any(ProductDTO.class))).thenReturn(product);
        when(productRepository.findById(Long.parseLong(id))).thenReturn(optionalProductDTO);
        Long result = productService.getProduct(id).getId();

        assertAll(() -> assertEquals(product.getId(), result));
    }

    @Test
    public void createProductTest() {
        doNothing().when(validator).numericIsMandatory(productREQDTO.getPrecio(), "Precio");
        when(productMapper.toProduct(any(ProductDTO.class))).thenReturn(product);
        when(productRepository.save(any())).thenReturn(productDTO);
        productService.createProduct(productREQDTO);
        verify(productMapper).toProduct(productDTO);

    }


    @Test
    public void modifyProductTest() {
        doNothing().when(validator).numericIsMandatory(anyString(),anyString());
        when(productRepository.findById(any())).thenReturn(optionalProductDTO);
        doNothing().when(validator).numericIsMandatory(productREQDTO.getPrecio(),"Precio");
        when(productRepository.save(any())).thenReturn(productDTO);
        productService.modifyProduct(productREQDTO,"7");

        verify(productRepository).findById(Long.parseLong(id));
        verify(productRepository).save(any());
    }

    private static List<ProductDTO> generarProductDTOList(EasyRandom generator) {
        productDTOList = generator.objects(ProductDTO.class, 9)
                .collect(Collectors.toList());
        ids = new ArrayList<>();
        ids.add(String.valueOf(product.getId()));
        productDTOList.get(5).setId(product.getId());
        productDTOList.get(7).setId(product.getId());

        return productDTOList;
    }

}