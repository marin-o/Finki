package com.bosa.eshop.model;

import lombok.Data;

@Data
public class Manufacturer {

    private Long id;
    private String name;
    private String address;

    public Manufacturer( String name, String address ) {
        this.id = (long) (Math.random() * 1000);
        this.name=name;
        this.address=address;
    }
}
