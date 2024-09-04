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
public class Factura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fecha;
    private int numero;
    private int total;


    //Creamos la relacion manytoone
    //usamos persist porque si borramos una factura el cliente debe permanecer
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;


    //Ahora creamos la relacion con detalle de factura
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //para que no me cree una tabla intermedia le pasamos el id_factura por medio de la relacion
    @JoinColumn(name = "factura_id")
    @Builder.Default //se coloca para que no se cree un puntero null
    private Set<DetalleFactura> facturas = new HashSet<>();



}
