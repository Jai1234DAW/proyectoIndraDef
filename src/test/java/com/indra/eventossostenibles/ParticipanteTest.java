/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.indra.eventossostenibles;


import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jaine
 */
public class ParticipanteTest {
    
    private Participante participante;
    private Evento evento1;
    private Evento evento2;

    @BeforeEach
    public void setup() {
        participante = new Participante(1, "Juan", "juan@mail.com", "pass123");

        evento1 = new Evento(1, "Evento 1", LocalDate.now(), LocalTime.of(10, 0),
                60, Evento.Estado.ACTIVO, Evento.Modalidad.PRESENCIAL,
                Ubicacion.crearPresencial("Lugar A"),
                new Categoria(1, "Cat1", "Desc1"),
                null);

        evento2 = new Evento(2, "Evento 2", LocalDate.now(), LocalTime.of(15, 0),
                90, Evento.Estado.ACTIVO, Evento.Modalidad.ONLINE,
                Ubicacion.crearOnline("link"),
                new Categoria(2, "Cat2", "Desc2"),
                null);
    }

    @Test
    public void testInscribirseEventoNuevo() {
        participante.inscribirse(evento1);

        assertEquals(1, participante.getNumInscripciones());
        assertEquals(1, participante.getInscripciones().size());
        assertEquals(evento1, participante.getInscripciones().get(0).getEvento());
        assertTrue(participante.getInscripciones().get(0).isActiva());
    }

    @Test
    public void testInscribirseEventoDuplicadoNoAgrega() {
        participante.inscribirse(evento1);
        participante.inscribirse(evento1); // segunda inscripción al mismo evento activa

        assertEquals(1, participante.getNumInscripciones()); // no se aumenta la cuenta
        assertEquals(1, participante.getInscripciones().size()); // sigue una sola inscripción activa
    }

    @Test
    public void testCancelarInscripcionExistente() {
        participante.inscribirse(evento1);
        participante.cancelarInscripcion(evento1);

        assertEquals(0, participante.getNumInscripciones());
        assertEquals(1, participante.getInscripciones().size()); // sigue la inscripción pero está inactiva
        assertFalse(participante.getInscripciones().get(0).isActiva());
    }

    @Test
    public void testCancelarInscripcionNoExistenteNoCambiaNada() {
        participante.inscribirse(evento1);
        participante.cancelarInscripcion(evento2); // No inscrito en evento2

        assertEquals(1, participante.getNumInscripciones());
        assertEquals(1, participante.getInscripciones().size());
        assertTrue(participante.getInscripciones().get(0).isActiva());
    }

    @Test
    public void testInscribirDespuesDeCancelar() {
        participante.inscribirse(evento1);
        participante.cancelarInscripcion(evento1);

        // En este punto permitir inscribirse de nuevo porque la inscripción activa no existe
        participante.inscribirse(evento1);

        // Ahora hay dos inscripciones, una inactiva y otra activa
        assertEquals(1, participante.getNumInscripciones());
        assertEquals(2, participante.getInscripciones().size());

        // La última inscripción está activa
        assertTrue(participante.getInscripciones().get(1).isActiva());
    }
}
