package com.project.storage.storagems.exceptions;

import com.project.storage.storagems.exceptions.menssages.Menssage;
import com.project.storage.storagems.exceptions.menssages.Type;
import org.springframework.http.HttpStatus;

public class ProductException extends MainException {
    public ProductException() {
        super();
        super.setHttpStatus(HttpStatus.BAD_REQUEST);
        super.setMen(new Menssage(Type.ERROR,"Error controlado", "Error controlado"));
    }
}
