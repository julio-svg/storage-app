package com.project.storage.item.service.validation;

import org.springframework.stereotype.Component;


public interface ItemValidation {
    void numericIsMandatory(String params,String field);
}
