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
public class UbicacionTest {
    
       @Test
    public void testCrearPresencial() {
        Ubicacion ubicacion = Ubicacion.crearPresencial("Calle 123");
        assertEquals("Calle 123", ubicacion.getDireccion());
        assertNull(ubicacion.getEnlace());
    }

    @Test
    public void testCrearOnline() {
        Ubicacion ubicacion = Ubicacion.crearOnline("https://evento.com");
        assertEquals("https://evento.com", ubicacion.getEnlace());
        assertNull(ubicacion.getDireccion());
    }

    @Test
    public void testToStringPresencial() {
        Ubicacion ubicacion = Ubicacion.crearPresencial("Calle 456");
        assertEquals("Calle 456", ubicacion.toString());
    }

    @Test
    public void testToStringOnline() {
        Ubicacion ubicacion = Ubicacion.crearOnline("https://eventoonline.com");
        assertEquals("https://eventoonline.com", ubicacion.toString());
    }
}
