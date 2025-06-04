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
public class UsuarioTest {
    
     @Test
    public void testConstructorYGetters() {
        Usuario usuario = new Usuario(1, "Pedro", "pedro@mail.com", "1234");
        assertEquals(1, usuario.getId());
        assertEquals("Pedro", usuario.getNombre());
        assertEquals("pedro@mail.com", usuario.getCorreo());
        assertEquals("1234", usuario.getContraseña());
    }

    @Test
    public void testSetters() {
        Usuario usuario = new Usuario(1, "Pedro", "pedro@mail.com", "1234");
        usuario.setNombre("Juan");
        usuario.setCorreo("juan@mail.com");
        usuario.setContraseña("abcd");

        assertEquals("Juan", usuario.getNombre());
        assertEquals("juan@mail.com", usuario.getCorreo());
        assertEquals("abcd", usuario.getContraseña());
    }
}
