/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.indra.eventossostenibles;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jaine
 */
public class OrganizadorTest {

    @Test
    public void testConstructorYGetters() {
        Organizador org = new Organizador(1, "Carla", "carla@mail.com", "1234", "Empresa", "555-9999");

        assertEquals("Carla", org.getNombre());
        assertEquals("Empresa", org.getTipo());
        assertEquals("555-9999", org.getTelefono());
        assertTrue(org.getEventosCreados().isEmpty());
    }

    @Test
    public void testCrearEvento() {
        Organizador org = crearOrganizadorEjemplo();
        org.crearEvento(1, "Charla", LocalDate.now(), LocalTime.of(10, 0), 90,
                Evento.Modalidad.PRESENCIAL, Ubicacion.crearPresencial("Sala A"),
                new Categoria(1, "Charla", "Informativa"));

        assertEquals(1, org.getEventosCreados().size());
        assertEquals("Charla", org.getEventosCreados().get(0).getNombre());
    }

    @Test
    public void testCrearVariosEventos() {
        Organizador org = crearOrganizadorEjemplo();

        for (int i = 0; i < 10; i++) {
            org.crearEvento(i, "Evento " + i, LocalDate.now(), LocalTime.of(9, 0), 60,
                    Evento.Modalidad.PRESENCIAL, Ubicacion.crearPresencial("Sala " + i),
                    new Categoria(i, "Cat" + i, "Desc"));
        }

        assertEquals(10, org.getEventosCreados().size());
    }

    @Test
    public void testCancelarEventoCorrectamente() {
        Organizador org = crearOrganizadorEjemplo();
        Evento ev = crearEventoEjemplo(org);
        org.getEventosCreados().add(ev);

        org.cancelarEvento(ev);
        assertEquals(Evento.Estado.CANCELADO, ev.getEstado());
    }

    @Test
    public void testCancelarEventoNoCreado() {
        Organizador org = crearOrganizadorEjemplo();
        Organizador otroOrg = crearOtroOrganizador();
        Evento ev = crearEventoEjemplo(otroOrg);

        org.cancelarEvento(ev); // No realiza ninguna acción
        assertEquals(Evento.Estado.ACTIVO, ev.getEstado());
    }

    @Test
    public void testCambiarEstadoCorrectamente() {
        Organizador org = crearOrganizadorEjemplo();
        Evento ev = crearEventoEjemplo(org);
        org.getEventosCreados().add(ev);

        org.cambiarEstadoEvento(ev, Evento.Estado.APLAZADO);
        assertEquals(Evento.Estado.APLAZADO, ev.getEstado());
    }

    @Test
    public void testCambiarEstadoNoCreado() {
        Organizador org = crearOrganizadorEjemplo();
        Organizador otro = crearOtroOrganizador();
        Evento ev = crearEventoEjemplo(otro);

        org.cambiarEstadoEvento(ev, Evento.Estado.CANCELADO);
        assertEquals(Evento.Estado.ACTIVO, ev.getEstado());
    }

    @Test
    public void testModificarEventoCorrectamente() {
        Organizador org = crearOrganizadorEjemplo();
        Evento ev = crearEventoEjemplo(org);
        org.getEventosCreados().add(ev);

        Categoria nuevaCategoria = new Categoria(2, "Taller", "Aprendizaje práctico");
        Ubicacion nuevaUbicacion = Ubicacion.crearPresencial("Sala B");

        org.modificarEvento(ev, "Nuevo Nombre", LocalDate.of(2025, 7, 1), 180,
                Evento.Modalidad.PRESENCIAL, nuevaUbicacion, nuevaCategoria);

        assertEquals("Nuevo Nombre", ev.getNombre());
        assertEquals(180, ev.getDuracion());
        assertEquals("Sala B", ev.getUbicacion().getDireccion());
    }

    @Test
    public void testModificarEventoNoCreado() {
        Organizador org = crearOrganizadorEjemplo();
        Evento ev = crearEventoEjemplo(crearOtroOrganizador());

        org.modificarEvento(ev, "Cambio", LocalDate.now(), 45,
                Evento.Modalidad.ONLINE, Ubicacion.crearOnline("Link"),
                new Categoria(5, "Tipo", "Desc"));

        assertNotEquals("Cambio", ev.getNombre());
    }

    @Test
    public void testCrearEventoConDuracionCeroONegativa() {
        Organizador org = crearOrganizadorEjemplo();

        assertThrows(IllegalArgumentException.class, () -> {
            org.crearEvento(99, "Evento raro", LocalDate.now(), LocalTime.now(), 0,
                    Evento.Modalidad.ONLINE, Ubicacion.crearOnline("Zoom"),
                    new Categoria(3, "Error", "Sin duración"));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            org.crearEvento(100, "Evento muy raro", LocalDate.now(), LocalTime.now(), -20,
                    Evento.Modalidad.PRESENCIAL, Ubicacion.crearPresencial("Sala X"),
                    new Categoria(4, "Error", "Duración negativa"));
        });
    }

    @Test
    public void testModificarEventoConNulos() {
        Organizador org = crearOrganizadorEjemplo();
        Evento ev = crearEventoEjemplo(org);
        org.getEventosCreados().add(ev);

        // Test de modificación con duración inválida (espera excepción)
        assertThrows(IllegalArgumentException.class, () -> {
            org.modificarEvento(ev, null, null, -10, null, null, null);
        });
    }

    // Métodos auxiliares
    private Organizador crearOrganizadorEjemplo() {
        return new Organizador(1, "Ana", "ana@mail.com", "pass", "Empresa", "123456");
    }

    private Organizador crearOtroOrganizador() {
        return new Organizador(2, "Luis", "luis@mail.com", "pass", "Institución", "987654");
    }

    private Evento crearEventoEjemplo(Organizador org) {
        return new Evento(100, "Test Event", LocalDate.now(), LocalTime.NOON, 90,
                Evento.Estado.ACTIVO, Evento.Modalidad.PRESENCIAL,
                Ubicacion.crearPresencial("Sala A"),
                new Categoria(1, "Cat", "Desc"), org);
    }
}
