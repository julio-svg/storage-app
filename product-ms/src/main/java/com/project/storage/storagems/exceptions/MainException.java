package com.project.storage.storagems.exceptions;

import com.project.storage.storagems.exceptions.messages.Message;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class MainException extends RuntimeException{

    public MainException() {
        super();
    }

    public MainException(Message men, HttpStatus httpStatus) {
        this.men = men;
        this.httpStatus = httpStatus;
    }

    Message men;
    HttpStatus httpStatus;

}
