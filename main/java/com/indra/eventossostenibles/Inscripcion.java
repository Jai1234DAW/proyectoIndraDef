/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indra.eventossostenibles;

import java.time.LocalDate;

/**
 *
 * @author jaine
 */
/*Aunque esto puede resolverse sin esta clase, se consideró la realización de esta clase como mejora al 
planteamiento del problema, en caso de que sea deseara tener un histórico de las inscripciones y agregar funcionalidades
a posteriori u otros atributos.*/
public class Inscripcion {
    private Participante participante;
    private Evento evento;
    private LocalDate fechaInscripcion;
    private boolean activa; //Para conocer el estado de la inscripción.

    //Constructor
    public Inscripcion(Participante participante, Evento evento) {
        this.participante = participante;
        this.evento = evento;
        this.fechaInscripcion = LocalDate.now();
        this.activa = true;
    }

    //Getter y Setter
    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
   

    // Para cancelar la inscripción
    /*Este método se utiliza para cambiar el valor de este atributo "activa",
    con la finalidad de tener un histórico de las inscripciones*/
    public void cancelar() {
        this.activa = false;
    }

    @Override
    public String toString() {
        return "Inscripción de " + participante.getNombre() + " en evento " + evento.getNombre() +
                " (Fecha: " + fechaInscripcion + ", Activa: " + activa + ")";
    }
}
