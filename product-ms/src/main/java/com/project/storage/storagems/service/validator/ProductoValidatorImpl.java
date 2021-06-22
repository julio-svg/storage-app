package com.project.storage.storagems.service.validator;


import com.project.storage.storagems.exceptions.NumberIsMandatoryException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Component
public class ProductoValidatorImpl implements ProductValidator{
    @Override
    public void numericIsMandatory(List<String> params,String field) {
         if(! Stream.ofNullable(params)
                 .flatMap(a -> a.stream())
                 .filter(Objects::nonNull)
                 .allMatch(param -> StringUtils.isNumeric(param))){
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
