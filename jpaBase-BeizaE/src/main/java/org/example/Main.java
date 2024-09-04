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
            Factura fact1 = new Factura();
            fact1.setNumero(12);
            fact1.setFecha("04/09/2024");

            // creamos el cliente
            Cliente cliente1 = new Cliente();
            cliente1.setApellido("Beiza");
            cliente1.setDni(33785422);
            cliente1.setNombre("Edgardo");

            //creamos el domicilio
            Domicilio domicilio1 = new Domicilio();
            domicilio1.setNumero(522);
            domicilio1.setNombreCalle("Alta Gracia");

            //Asignamos el domicilio al cliente (bidireccional)
            cliente1.setDomicilio(domicilio1);

            //Asignamos el cliente a la factura
            fact1.setCliente(cliente1);

            // Creamos las categorias
            Categoria alimperecederos = new Categoria();
            alimperecederos.setDenominacion("Alim Perecederos");
            Categoria lacteos = new Categoria();
            lacteos.setDenominacion("Lacteos");
            Categoria limpieza = new Categoria();
            limpieza.setDenominacion("Limpieza");

            // Creamos articulos
            Articulo art1 = new Articulo();
            Articulo art2 = new Articulo();
            Articulo art3 = new Articulo();

            //Art 1
            art1.setCantidad(5);
            art1.setDenominacion("Leche larga vida 1lt");
            art1.setPrecio(20);
            art1.getCategorias().add(lacteos);

            //Art 2
            art2.setCantidad(10);
            art2.setDenominacion("Cif crema");
            art2.setPrecio(350);
            art2.getCategorias().add(limpieza);

            //Art 3
            art3.setCantidad(8);
            art3.setDenominacion("Harina trigo 0000");
            art3.setPrecio(1500);
            art3.getCategorias().add(alimperecederos);

            // Ahora a las categorias le asignamos los articulos
            lacteos.getArticulos().add(art1);
            limpieza.getArticulos().add(art2);
            alimperecederos.getArticulos().add(art3);

            //Creamos el detalle de factura
            DetalleFactura detallefac = new DetalleFactura();
            detallefac.setCantidad(2);
            detallefac.setArticulo(art1);
            detallefac.setSubtotal(detallefac.getCantidad()*detallefac.getArticulo().getPrecio());

            art1.getDetalle().add(detallefac);
            detallefac.setFactura(fact1);

            //Creamos otro detalle de factura
            DetalleFactura detallefac2 = new DetalleFactura();
            detallefac2.setCantidad(5);
            detallefac2.setArticulo(art2);
            detallefac2.setSubtotal(detallefac2.getCantidad()*detallefac2.getArticulo().getPrecio());

            art1.getDetalle().add(detallefac2);
            detallefac2.setFactura(fact1);

            //Creamos otro detalle de factura
            DetalleFactura detallefac3 = new DetalleFactura();
            detallefac3.setCantidad(3);
            detallefac3.setArticulo(art3);
            detallefac3.setSubtotal(detallefac3.getCantidad()*detallefac3.getArticulo().getPrecio());

            art1.getDetalle().add(detallefac3);
            detallefac3.setFactura(fact1);


            //le pasamos el detalle a la factura
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
