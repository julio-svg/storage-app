package com.project.storage.storagems.exceptions;

import com.project.storage.storagems.exceptions.menssages.Menssage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class MainException extends RuntimeException{

    Menssage men;

    HttpStatus httpStatus;

    public MainException() {
        super();
    }
}
