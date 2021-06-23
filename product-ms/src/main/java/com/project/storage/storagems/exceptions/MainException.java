package com.project.storage.storagems.exceptions;

import com.project.storage.storagems.exceptions.messages.Message;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class MainException extends RuntimeException{

    Message men;

    HttpStatus httpStatus;

    public MainException() {
        super();
    }
}
