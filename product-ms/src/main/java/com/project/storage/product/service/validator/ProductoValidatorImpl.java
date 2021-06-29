package com.project.storage.product.service.validator;


import com.project.storage.product.exceptions.NumberIsMandatoryException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Component
public class ProductoValidatorImpl implements ProductValidator{
    @Override
    public void numericIsMandatory(List<String> params,String field) {
         if(! Stream.ofNullable(params)
                 .flatMap(Collection::stream)
                 .filter(Objects::nonNull)
                 .allMatch(StringUtils::isNumeric)){
            throw new NumberIsMandatoryException(field);
        }
    }
    @Override
    public void numericIsMandatory(String param,String field) {
        if(Objects.nonNull(param) && !StringUtils.isNumeric(param)){
            throw new NumberIsMandatoryException(field);
        }
    }
}
