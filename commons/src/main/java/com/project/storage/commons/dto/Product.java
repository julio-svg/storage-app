package com.project.storage.commons.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Product implements Serializable {

    private Long id;
    private String nombre;
    private Double precio;
    private Date createAt;

}
