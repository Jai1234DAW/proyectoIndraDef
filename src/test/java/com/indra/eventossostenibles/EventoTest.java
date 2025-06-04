/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.indra.eventossostenibles;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jaine
 */
public class EventoTest {
  @Test
    void crearEventoPresencialConDireccionValida() {
        Ubicacion ubicacion = new Ubicacion("Calle 123", null);
        Categoria cat = new Categoria(1, "Conferencia", "Descripción");
        Organizador org = new Organizador(1, "Org", "email", "pass", "empresa", "123");

        Evento evento = new Evento(1, "Evento 1", LocalDate.now(), LocalTime.of(10, 0),
                60, Evento.Estado.ACTIVO, Evento.Modalidad.PRESENCIAL, ubicacion, cat, org);

        assertEquals("Evento 1", evento.getNombre());
    }

    @Test
    void crearEventoOnlineConEnlaceValido() {
        Ubicacion ubicacion = new Ubicacion(null, "https://zoom.us/abc123");
        Categoria cat = new Categoria(1, "Webinar", "Online");
        Organizador org = new Organizador(2, "Org2", "email", "pass", "empresa", "123");

        Evento evento = new Evento(2, "Evento Online", LocalDate.now(), LocalTime.of(14, 0),
                90, Evento.Estado.ACTIVO, Evento.Modalidad.ONLINE, ubicacion, cat, org);

        assertEquals("Evento Online", evento.getNombre());
    }

    @Test
    void errorAlCrearEventoPresencialSinDireccion() {
        Ubicacion ubicacion = new Ubicacion(null, null);  // Sin dirección
        Categoria cat = new Categoria(1, "Conferencia", "Desc");
        Organizador org = new Organizador(3, "Org3", "email", "pass", "empresa", "123");

        assertThrows(IllegalArgumentException.class, () -> {
            new Evento(3, "Evento Inválido", LocalDate.now(), LocalTime.now(),
                    30, Evento.Estado.ACTIVO, Evento.Modalidad.PRESENCIAL, ubicacion, cat, org);
        });
    }

    @Test
    void errorAlCambiarAModalidadOnlineSinEnlace() {
        Ubicacion ubicacion = new Ubicacion("Dirección válida", null);
        Categoria cat = new Categoria(1, "Categoria", "Desc");
        Organizador org = new Organizador(4, "Org", "correo", "123", "Empresa", "123");

        Evento evento = new Evento(4, "Mi Evento", LocalDate.now(), LocalTime.now(),
                60, Evento.Estado.ACTIVO, Evento.Modalidad.PRESENCIAL, ubicacion, cat, org);

        assertThrows(IllegalArgumentException.class, () -> {
            evento.setModalidad(Evento.Modalidad.ONLINE);
        });
    }

    @Test
    void listadoInscripcionesEsInmodificable() {
        Ubicacion ubicacion = new Ubicacion("Calle", null);
        Categoria cat = new Categoria(5, "Tipo", "Desc");
        Organizador org = new Organizador(5, "Org", "correo", "123", "Empresa", "123");

        Evento evento = new Evento(5, "Evento", LocalDate.now(), LocalTime.now(),
                60, Evento.Estado.ACTIVO, Evento.Modalidad.PRESENCIAL, ubicacion, cat, org);

        List<Inscripcion> lista = evento.getListadoInscripciones();
        assertThrows(UnsupportedOperationException.class, () -> {
            lista.add(new Inscripcion(null, evento));
        });
    }
}
