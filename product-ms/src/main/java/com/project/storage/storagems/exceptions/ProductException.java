package com.project.storage.storagems.exceptions;

import com.project.storage.storagems.exceptions.messages.Message;
import com.project.storage.storagems.exceptions.messages.Type;
import org.springframework.http.HttpStatus;

public class ProductException extends MainException {
    public ProductException() {
        super();
        super.setHttpStatus(HttpStatus.BAD_REQUEST);
        super.setMen(new Message(Type.ERROR,"Error controlado", "Error controlado"));
    }
}
