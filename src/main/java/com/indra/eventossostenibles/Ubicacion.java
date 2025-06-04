/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indra.eventossostenibles;

/**
 *
 * @author jaine
 */
/* Aunque de momento no es estrictamente necesario se plantea la clase ubicación porque es una mejora
para el futuro de esta aplicación. Por una posible evolución del sistema, si más adelante se agregan más campos como ciudad, país, 
coordenadas GPS, plataforma online, contraseña del evento, etc. Ubicacion resultaría ser una clase mucho más limpia y escalable.
Por otro lado la creación de esta clase mantiene la clase Evento más limpia.*/
public class Ubicacion {

    private String direccion;  // Solo para modalidad presencial
    private String enlace;     // Solo para modalidad online
    

    //Constructor en caso de que a futuro la modalidad sea mixta
     public Ubicacion(String direccion, String enlace) {
        this.direccion = direccion;
        this.enlace = enlace;
    }

    // Constructor para presencial
    public static Ubicacion crearPresencial(String direccion) {
        return new Ubicacion(direccion, null);
    }

    // Constructor para online
    public static Ubicacion crearOnline(String enlace) {
        return new Ubicacion(null, enlace);
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEnlace() {
        return enlace;
    }

    //Método toString para imprimir un solo valor, según la modalidad indicada en la clase Evento.
    public String toString() {
        return (direccion != null) ? direccion : enlace;
    }
}

