package com.project.storage.item.exception.handler;

import com.project.storage.commons.exceptions.ClienteException;
import com.project.storage.commons.exceptions.ServerException;
import com.project.storage.commons.exceptions.messages.Message;
import com.project.storage.commons.exceptions.messages.Type;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ItemExceptionHandler {

    @ExceptionHandler(ClienteException.class)
    protected ResponseEntity<Message> mainException(ClienteException ex){
        return new ResponseEntity(new Message(Type.ERROR,ex.getMessage(),"Error en cliente"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServerException.class)
    protected ResponseEntity<Message> mainException(ServerException ex){
        return new ResponseEntity(new Message(Type.ERROR,ex.getMessage(),"Error en servicio productos"), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Message> Exceptions(Exception ex){
        return new ResponseEntity<>(new Message(Type.ERROR,ex.getMessage(),"generic error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
