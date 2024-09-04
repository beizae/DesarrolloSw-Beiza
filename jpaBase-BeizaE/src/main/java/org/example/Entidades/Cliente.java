package org.example.Entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

// persistence
@Entity
@Table
//Lombok
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@ToString
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String apellido;
    // con esto validamos que el dni sea unico y no hayan 2 iguales
    @Column(name = "dni", unique = true)
    private int dni;
    private String nombre;


    // Relacion con domicilio
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_domicilio")
    private Domicilio domicilio;


    // Relacion con factura
    @OneToMany(mappedBy = "cliente")
    private Set<Factura> facturas = new HashSet<>();

}
