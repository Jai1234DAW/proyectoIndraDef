/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indra.eventossostenibles;

import com.indra.eventossostenibles.Evento.Modalidad;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jaine
 */
public class Organizador extends Usuario {

    private String tipo; //puede ser una empresa, institucion, etc. 
    private String telefono;
    private List<Evento> eventosCreados; //Aunque no lo especifica el universo de Discurso, se consideró este atributo, para tener un registro.

    public Organizador(int id, String nombre, String correo, String contrasena, String tipo, String telefono) {
        super(id, nombre, correo, contrasena);
        this.tipo = tipo;
        this.telefono = telefono;
        this.eventosCreados = new ArrayList<>();
    }

    //Getters y Setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Evento> getEventosCreados() {
        return eventosCreados;
    }

    public void setEventosCreados(List<Evento> eventosCreados) {
        this.eventosCreados = eventosCreados;
    }
   

    // Método para crear un evento y añadirlo a la lista de eventos creados
     public void crearEvento(int id,String nombre, LocalDate fecha,LocalTime horaInicio,
             int duracion, Modalidad modalidad,Ubicacion ubicacion, Categoria categoria){
         
         Evento.Estado estadoInicial=Evento.Estado.ACTIVO; //valor por defecto del estado;
         Evento nuevoEvento =new Evento(id,nombre,fecha,horaInicio,duracion,estadoInicial,modalidad,ubicacion,categoria,this);
         eventosCreados.add(nuevoEvento);
     }

    // Método para cancelar un evento, se realizó aparte para simplificar cuando se desee cancelar.
     //Aunque es suficiente con el método de abajo, el enunciado pedía especificamente un método para cancelar por lo cual se
     //trabajó de esta manera. 
    public void cancelarEvento(Evento evento) {
        if (eventosCreados.contains(evento)) {
            evento.setEstado(Evento.Estado.CANCELADO);
        } else {
            System.out.println("No puedes cancelar un evento que no has creado.");
        }
    }

    // Cambiar estado del evento (activo, aplazado, cancelado)
    public void cambiarEstadoEvento(Evento evento, Evento.Estado nuevoEstado) {
        if (eventosCreados.contains(evento)) {
            evento.setEstado(nuevoEstado);
        } else {
            System.out.println("No puedes cambiar el estado de un evento que no has creado.");
        }
    }

    // Modificar detalles básicos de un evento
    public void modificarEvento(Evento evento, String nuevoNombre, LocalDate nuevaFecha, int nuevaDuracion, Modalidad nuevaModalidad, Ubicacion nuevaUbicacion, Categoria nuevaCategoria) {
        if (eventosCreados.contains(evento)) {
            evento.setNombre(nuevoNombre);
            evento.setFecha(nuevaFecha);
            evento.setDuracion(nuevaDuracion);
            evento.setModalidad(nuevaModalidad);
            evento.setUbicacion(nuevaUbicacion);
            evento.setCategoria(nuevaCategoria);
        } else {
            System.out.println("No puedes modificar un evento que no has creado.");
        }
    }

    @Override
    public String toString() {
        return "Organizador{" + "tipo=" + tipo + ", telefono=" + telefono + '}';
    }
}
