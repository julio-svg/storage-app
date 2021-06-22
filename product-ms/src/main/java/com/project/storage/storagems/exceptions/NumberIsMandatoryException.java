package com.project.storage.storagems.exceptions;

import com.project.storage.storagems.exceptions.menssages.Menssage;
import com.project.storage.storagems.exceptions.menssages.Type;
import org.springframework.http.HttpStatus;

public class NumberIsMandatoryException extends MainException{


    public NumberIsMandatoryException(String campo) {
        super();
        super.setHttpStatus(HttpStatus.BAD_REQUEST);
        super.setMen(new Menssage(Type.ERROR,String.format("el campo/parametro %s debe ser numerico", campo), "Numeric mandatory"));

    }
}
