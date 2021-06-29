package com.project.storage.product.exceptions.handler;

import com.project.storage.product.exceptions.MainException;
import com.project.storage.product.exceptions.messages.Message;
import com.project.storage.product.exceptions.messages.Type;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler {
    @ExceptionHandler(MainException.class)
    protected ResponseEntity<Message> mainException(MainException ex){
        return new ResponseEntity(ex.getMen(),ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Message> Exceptions(Exception ex){
        return new ResponseEntity<>(new Message(Type.ERROR,ex.getMessage(),"generic error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
