package org.example.Entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

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
public class DetalleFactura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int cantidad;
    private int subtotal;


    //Creamos la relacion con articulo muchos a uno
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_articulo")
    private Articulo articulo;


    // creamos la relacion con factura
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_detalle_factura")
    private Factura factura;


}
