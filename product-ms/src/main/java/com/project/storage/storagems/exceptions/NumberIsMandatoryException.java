package com.project.storage.storagems.exceptions;

import com.project.storage.storagems.exceptions.messages.Message;
import com.project.storage.storagems.exceptions.messages.Type;
import org.springframework.http.HttpStatus;

public class NumberIsMandatoryException extends MainException{


    public NumberIsMandatoryException(String campo) {
        super();
        super.setHttpStatus(HttpStatus.BAD_REQUEST);
        super.setMen(new Message(Type.ERROR,String.format("el campo/parametro %s debe ser numerico", campo), "Numeric mandatory"));

    }
}
