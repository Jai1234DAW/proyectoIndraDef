/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.indra.eventossostenibles;


import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jaine
 */
public class InscripcionTest {
    
    private Participante participante;
    private Evento evento;

    @BeforeEach
    public void setUp() {
        // Objetos mínimos para pruebas
        participante = new Participante(1, "Ana", "ana@mail.com", "1234");
        Categoria categoria = new Categoria(1, "Taller Tech", "Eventos tech");
        Ubicacion ubicacion = Ubicacion.crearOnline("www.evento.com");

        Organizador organizador = new Organizador(2, "Org", "org@mail.com", "pass", "Empresa", "12345");

        evento = new Evento(
                10,
                "Taller",
                LocalDate.of(2025, 6, 10),
                java.time.LocalTime.of(10, 0),
                120,
                Evento.Estado.ACTIVO,
                Evento.Modalidad.ONLINE,
                ubicacion,
                categoria,
                organizador
        );
    }

    @Test
    public void testCreacionInscripcionValida() {
        Inscripcion inscripcion = new Inscripcion(participante, evento);

        assertEquals(participante, inscripcion.getParticipante());
        assertEquals(evento, inscripcion.getEvento());
        assertEquals(LocalDate.now(), inscripcion.getFechaInscripcion());
        assertTrue(inscripcion.isActiva());
    }

    @Test
    public void testCancelarInscripcion() {
        Inscripcion inscripcion = new Inscripcion(participante, evento);
        inscripcion.cancelar();

        assertFalse(inscripcion.isActiva(), "La inscripción debe estar inactiva después de cancelar");
    }

    @Test
    public void testSetters() {
        Inscripcion inscripcion = new Inscripcion(participante, evento);

        Participante nuevoParticipante = new Participante(3, "Carlos", "carlos@mail.com", "abc123");
        inscripcion.setParticipante(nuevoParticipante);

        assertEquals("Carlos", inscripcion.getParticipante().getNombre());

        inscripcion.setActiva(false);
        assertFalse(inscripcion.isActiva());

        LocalDate nuevaFecha = LocalDate.of(2025, 1, 1);
        inscripcion.setFechaInscripcion(nuevaFecha);
        assertEquals(nuevaFecha, inscripcion.getFechaInscripcion());
    }

    @Test
    public void testToStringContieneNombreParticipanteYEvento() {
        Inscripcion inscripcion = new Inscripcion(participante, evento);
        String texto = inscripcion.toString();

        assertTrue(texto.contains("Ana"));
        assertTrue(texto.contains("Taller"));
        assertTrue(texto.contains("Activa: true"));
    }

    @Test
    public void testCrearInscripcionConParticipanteNulo() {
        assertThrows(NullPointerException.class, () -> {
            new Inscripcion(null, evento).toString(); // El toString lanza error por getNombre()
        });
    }

    @Test
    public void testCrearInscripcionConEventoNulo() {
        assertThrows(NullPointerException.class, () -> {
            new Inscripcion(participante, null).toString(); // El toString lanza error por getNombre()
        });
    }

    @Test
    public void testModificarParticipanteYEvento() {
        Inscripcion inscripcion = new Inscripcion(participante, evento);

        Participante nuevoP = new Participante(99, "Pedro", "pedro@mail.com", "xx");
        Evento nuevoE = new Evento(20, "Nueva Charla", LocalDate.of(2025, 7, 1),
                java.time.LocalTime.of(9, 0), 90, Evento.Estado.ACTIVO,
                Evento.Modalidad.ONLINE, evento.getUbicacion(),
                evento.getCategoria(), evento.getOrganizador());

        inscripcion.setParticipante(nuevoP);
        inscripcion.setEvento(nuevoE);

        assertEquals("Pedro", inscripcion.getParticipante().getNombre());
        assertEquals("Nueva Charla", inscripcion.getEvento().getNombre());
    }
}
