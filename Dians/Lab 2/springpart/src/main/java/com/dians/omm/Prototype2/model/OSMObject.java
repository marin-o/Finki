package com.dians.omm.Prototype2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "OSMObject")
@NoArgsConstructor
@AllArgsConstructor
public class OSMObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String type;
    @NotNull
    private String name;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
}
