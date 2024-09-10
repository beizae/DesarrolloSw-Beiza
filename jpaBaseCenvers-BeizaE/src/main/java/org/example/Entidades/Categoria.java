package org.example.Entidades;

import lombok.*;
import org.hibernate.envers.Audited;

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
@Audited
public class Categoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String denominacion;


    //Creamos la relacion con otro conjunto llamando al conj creado en articulos
    // y le decimos que esta mapeada por la otra clase
    @ManyToMany(mappedBy = "categorias")
    @Builder.Default
    private Set<Articulo> articulos = new HashSet<>();


}
