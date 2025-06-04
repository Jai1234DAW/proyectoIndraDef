/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indra.eventossostenibles;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jaine
 */
public class Participante extends Usuario {

    private int numInscripciones;
    private List<Inscripcion> listadoInscripciones;

    //Constructor 
    public Participante(int id, String nombre, String correo, String contraseña) {
        super(id, nombre, correo, contraseña);
        this.numInscripciones = 0;
        this.listadoInscripciones = new ArrayList<>();
    }

    //Getters y Setters
    public int getNumInscripciones() {
        return numInscripciones;
    }

    public void setNumInscripciones(int numInscripciones) {
        this.numInscripciones = numInscripciones;
    }

    public List<Inscripcion> getInscripciones() {
        return listadoInscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.listadoInscripciones = inscripciones;
    }

    // Método para inscribirse a un evento
    public void inscribirse(Evento evento) {
        for (Inscripcion i : listadoInscripciones) {
            if (i.getEvento().equals(evento) && i.isActiva()) {
                System.out.println("Ya estás inscrito en este evento.");
                return;
            }
        }

        Inscripcion nueva = new Inscripcion(this, evento);
        listadoInscripciones.add(nueva);
        evento.agregarInscripcion(nueva);
        numInscripciones++;

        System.out.println("Inscripción realizada a: " + evento.getNombre());
    }

     public void cancelarInscripcion(Evento evento) {
        for (Inscripcion i : listadoInscripciones) {
            if (i.getEvento().equals(evento) && i.isActiva()) {
                i.cancelar(); // Marca la inscripción como inactiva
                evento.eliminarInscripcion(i); //Se elimina del evento la inscripción, para no causar confusión en el listado solo estarán aquellas inscripciones activas. 
                numInscripciones--;
                System.out.println("Inscripción cancelada de: " + evento.getNombre());
                return;
            }
        }

        System.out.println("No tienes inscripción activa en ese evento.");
    }
}

