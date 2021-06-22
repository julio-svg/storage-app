package com.project.storage.storagems.controller.rdto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductREQDTO implements Serializable {
    private String nombre;
    private String precio;
}
