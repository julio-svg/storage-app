package com.project.storage.item.service.validation;


import com.project.storage.commons.exceptions.NumberIsMandatoryException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ItemValidationImpl implements ItemValidation{
    @Override
    public void numericIsMandatory(String param, String field) {
        if(Objects.nonNull(param) && !StringUtils.isNumeric(param)){
            throw new NumberIsMandatoryException(String.format("El campo/parametro %s debe ser numerico", field));
        }
    }
}
