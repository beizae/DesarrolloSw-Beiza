package org.example;



import org.example.Entidades.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            // Persistir una nueva entidad
            entityManager.getTransaction().begin();



            //Creamos la factura
            Factura fact1 = Factura.builder()
                    .numero(12)
                    .fecha("04/09/2024")
                    .build();


            // creamos el cliente
            Cliente cliente1 = Cliente.builder()
                .apellido("Beiza")
                .dni(33785422)
                .nombre("Edgardo")
                .build();

            //creamos el domicilio
            Domicilio domicilio1 = Domicilio.builder()
                    .numero(522)
                    .nombreCalle("Alta Gracia")
                    .build();


            //Asignamos el domicilio al cliente (bidireccional)
            cliente1.setDomicilio(domicilio1);

            //Asignamos el cliente a la factura
            fact1.setCliente(cliente1);

            // Creamos las categorias
            Categoria alimperecederos = Categoria.builder()
                    .denominacion("Alim Perecederos")
                    .build();
            Categoria lacteos = Categoria.builder()
                    .denominacion("Lacteos")
                    .build();
            Categoria limpieza = Categoria.builder()
                    .denominacion("Limpieza")
                    .build();

            // Creamos articulos
            //art 1
            Articulo art1 = Articulo.builder()
                    .cantidad(5)
                    .denominacion("Leche larga vida 1lt")
                    .precio(20)
                    .build();
            art1.getCategorias().add(lacteos);

            //Art 2
            Articulo art2 = Articulo.builder()
                    .cantidad(10)
                    .denominacion("Cif crema")
                    .precio(350)
                    .build();
            art2.getCategorias().add(limpieza);


            //Art 3
            Articulo art3 = Articulo.builder()
                    .cantidad(8)
                    .denominacion("Harina trigo 0000")
                    .precio(1500)
                    .build();

            art3.getCategorias().add(alimperecederos);

            // Ahora a las categorias le asignamos los articulos
            lacteos.getArticulos().add(art1);
            limpieza.getArticulos().add(art2);
            alimperecederos.getArticulos().add(art3);

            //Creamos el detalle de factura
            DetalleFactura detallefac = DetalleFactura.builder()
                    .cantidad(2)
                    .articulo(art1)
                    .build();
            detallefac.setSubtotal(detallefac.getCantidad()*detallefac.getArticulo().getPrecio());

            art1.getDetalle().add(detallefac);
            detallefac.setFactura(fact1);

            //Creamos otro detalle de factura
            DetalleFactura detallefac2 = DetalleFactura.builder()
                    .cantidad(5)
                    .articulo(art2)
                    .build();
            detallefac2.setSubtotal(detallefac2.getCantidad()*detallefac2.getArticulo().getPrecio());

            art1.getDetalle().add(detallefac2);
            detallefac2.setFactura(fact1);

            //Creamos otro detalle de factura
            DetalleFactura detallefac3 = DetalleFactura.builder()
                    .cantidad(3)
                    .articulo(art3)
                    .build();
            detallefac3.setSubtotal(detallefac3.getCantidad()*detallefac3.getArticulo().getPrecio());

            art1.getDetalle().add(detallefac3);
            detallefac3.setFactura(fact1);

            fact1.getFacturas().add(detallefac);
            fact1.getFacturas().add(detallefac2);
            fact1.getFacturas().add(detallefac3);

            // sumamos los subtotales y se lo asignamos al total de la factura
            fact1.setTotal(detallefac.getSubtotal()+detallefac2.getSubtotal()+detallefac3.getSubtotal());




            entityManager.persist(fact1);
            entityManager.persist(art1);
            entityManager.getTransaction().commit();



        }catch (Exception e){

            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("Error, algo salió mal");}




        // Cerrar el EntityManager y el EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}
