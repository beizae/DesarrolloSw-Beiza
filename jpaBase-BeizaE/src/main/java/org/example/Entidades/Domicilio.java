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
public class Domicilio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private  String nombreCalle;
    private  int numero;


    //Relacion con cliente
    @OneToOne(mappedBy = "domicilio")
    private Cliente cliente;


}
