package com.project.storage.product.service.validator;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductValidator {

    void numericIsMandatory(List<String> params,String field);
    void numericIsMandatory(String params,String field);
}
