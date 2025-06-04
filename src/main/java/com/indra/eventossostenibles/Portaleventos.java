/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.indra.eventossostenibles;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author jaine
 */
public class Portaleventos {

    public static void main(String[] args) {

        // Crear una categoría
        Categoria conferenciasTecnológicas = new Categoria(1, "Conferencias Tecnológicas", "Talleres relacionados con tecnología y programación");

        // Crear ubicaciones
        Ubicacion presencial = Ubicacion.crearPresencial("Av. Siempre Viva 742");
        Ubicacion online = Ubicacion.crearOnline("https://zoom.us/mi-evento");

        // Crear un organizador
        Organizador org = new Organizador(100, "Ana Pérez", "ana@evento.com", "1234", "Empresa", "555-123456");

        // Crear evento presencial
        org.crearEvento(
                200,
                "Conferencia de Java",
                LocalDate.of(2025, 7, 15),
                LocalTime.of(10, 0),
                120,
                Evento.Modalidad.PRESENCIAL,
                presencial,
                conferenciasTecnológicas
        );

        // Crear evento online
        org.crearEvento(
                201,
                "Webinar de Python",
                LocalDate.of(2025, 7, 20),
                LocalTime.of(18, 0),
                90,
                Evento.Modalidad.ONLINE,
                online,
                conferenciasTecnológicas
        );

        // Mostrar eventos creados por el organizador
        System.out.println("\nEventos creados por el organizador:");
        for (Evento e : org.getEventosCreados()) {
            System.out.println(e);
        }

        // Crear un participante
        Participante participante = new Participante(300, "Carlos Ruiz", "carlos@correo.com", "pass123");

        // El participante se inscribe al primer evento
        Evento eventoJava = org.getEventosCreados().get(0);
        participante.inscribirse(eventoJava);

        // El participante intenta inscribirse de nuevo al mismo evento
        participante.inscribirse(eventoJava);

        // El participante cancela la inscripción
        participante.cancelarInscripcion(eventoJava);

        // El participante intenta cancelar de nuevo
        participante.cancelarInscripcion(eventoJava);
    }
}
