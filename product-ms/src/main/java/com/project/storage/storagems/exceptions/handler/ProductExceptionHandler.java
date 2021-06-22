package com.project.storage.storagems.exceptions.handler;

import com.project.storage.storagems.exceptions.MainException;
import com.project.storage.storagems.exceptions.menssages.Menssage;
import com.project.storage.storagems.exceptions.menssages.Type;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler {
    @ExceptionHandler(MainException.class)
    protected ResponseEntity<Menssage> mainException(MainException ex){
        return new ResponseEntity(ex.getMen(),ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Menssage> Exceptions(Exception ex){
        return new ResponseEntity<>(new Menssage(Type.ERROR,ex.getMessage(),"generic error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
