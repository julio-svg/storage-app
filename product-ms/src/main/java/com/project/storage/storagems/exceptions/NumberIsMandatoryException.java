package com.project.storage.storagems.exceptions;

import com.project.storage.storagems.exceptions.messages.Message;
import com.project.storage.storagems.exceptions.messages.Type;
import org.springframework.http.HttpStatus;

public class NumberIsMandatoryException extends MainException {

    public NumberIsMandatoryException(String campo) {
        super(new Message(Type.ERROR, String.format("El campo/parametro %s debe ser numerico", campo), "Numeric mandatory"),
                HttpStatus.BAD_REQUEST);
    }
}
