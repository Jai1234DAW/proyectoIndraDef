/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.indra.eventossostenibles;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jaine
 */
public class CategoriaTest {
    
      
      @Test
    public void testGetNombre() {
        Categoria cat = new Categoria(1,"Deporte", "Eventos deportivos");
        assertEquals("Deporte", cat.getNombre());
    }

    @Test
    public void testSetNombre() {
        Categoria cat = new Categoria(1, "Deporte", "Eventos deportivos");
        cat.setNombre("Cultura");
        assertEquals("Cultura", cat.getNombre());
    }

    @Test
    public void testGetDescripcion() {
        Categoria cat = new Categoria(1, "Deporte", "Eventos deportivos");
        assertEquals("Eventos deportivos", cat.getDescripcion());
    }

    @Test
    public void testSetDescripcion() {
        Categoria cat = new Categoria(1, "Deporte", "Eventos deportivos");
        cat.setDescripcion("Eventos culturales");
        assertEquals("Eventos culturales", cat.getDescripcion());
    }

    @Test
    public void testToString() {
        Categoria cat = new Categoria(1, "Deporte", "Eventos deportivos");
       String expected = "Categoria{id=1, nombre=Deporte, descripcion=Eventos deportivos}";
        assertEquals(expected, cat.toString());
    }   
    
}
