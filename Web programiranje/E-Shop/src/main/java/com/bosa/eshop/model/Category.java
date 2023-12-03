package com.bosa.eshop.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Category{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 4000)
    private String description;

    public Category( String name, String description ) {
        this.name=name;
        this.description=description;
    }


}
