package com.project.storage.product.exceptions.handler;


import com.project.storage.commons.exceptions.NumberIsMandatoryException;
import com.project.storage.commons.exceptions.messages.Message;
import com.project.storage.commons.exceptions.messages.Type;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Message> numberIsMandatory(NumberIsMandatoryException ex){
        return new ResponseEntity(new Message(Type.ERROR,ex.getMessage(),"generic error"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Message> Exceptions(Exception ex){
        return new ResponseEntity<>(new Message(Type.ERROR,ex.getMessage(),"generic error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
