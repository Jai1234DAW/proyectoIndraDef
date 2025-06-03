/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indra.eventossostenibles;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author jaine
 */
public class Evento {

    private int id;
    private String nombre;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private int duracion;
    private Estado estado;
    private Modalidad modalidad;
    private Ubicacion ubicacion;

    private Categoria categoria;
    private Organizador organizador;
    private List<Inscripcion> listadoInscripciones;

    //Se plantea de esta manera estos tres estado, aunque no especifíca directamente el enunciado que sean 3.
    public enum Estado {
        ACTIVO, CANCELADO, APLAZADO
    }

    public enum Modalidad {
        PRESENCIAL, ONLINE
    }

    public Evento(int id, String nombre, LocalDate fecha, LocalTime horaInicio, int duracion,
            Estado estado, Modalidad modalidad, Ubicacion ubicacion, Categoria categoria, Organizador organizador) {

        //Se hacen estas comprobaciones en el constructor para corroborar que exista coherencia entre la modalidad del evento
        //y su ubicación
        if (modalidad == null || ubicacion == null) {
            throw new IllegalArgumentException("Modalidad y ubicación no pueden ser nulos.");
        }

        if ((modalidad == Modalidad.ONLINE && ubicacion.getEnlace() == null)
                || (modalidad == Modalidad.PRESENCIAL && ubicacion.getDireccion() == null)) {
            throw new IllegalArgumentException("La ubicación no es coherente con la modalidad.");
        }

        //Se comprueba que la duración sea positiva
        if (duracion <= 0) {
            throw new IllegalArgumentException("La duración debe ser positiva.");
        }

        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.duracion = duracion;
        this.estado = estado;
        this.modalidad = modalidad;
        this.ubicacion = ubicacion;
        this.categoria = categoria;
        this.organizador = organizador;
        this.listadoInscripciones = new ArrayList<>();
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        if (duracion <= 0) {
            throw new IllegalArgumentException("La duración debe ser positiva.");
        }
        this.duracion = duracion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    //Se comprueba nuevamente en este método la coherencia entre la modalidad y ubicación
    public void setModalidad(Modalidad modalidad) {
        if (modalidad == null) {
            throw new IllegalArgumentException("La modalidad no puede ser nula.");
        }
        if ((modalidad == Modalidad.ONLINE && this.ubicacion.getEnlace() == null)
                || (modalidad == Modalidad.PRESENCIAL && this.ubicacion.getDireccion() == null)) {
            throw new IllegalArgumentException("Modalidad no coincide con la ubicación actual.");
        }
        this.modalidad = modalidad;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        if (ubicacion == null) {
            throw new IllegalArgumentException("La ubicación no puede ser nula.");
        }
        if ((this.modalidad == Modalidad.ONLINE && ubicacion.getEnlace() == null)
                || (this.modalidad == Modalidad.PRESENCIAL && ubicacion.getDireccion() == null)) {
            throw new IllegalArgumentException("Ubicación no válida para la modalidad actual.");
        }
        this.ubicacion = ubicacion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Organizador getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Organizador organizador) {
        this.organizador = organizador;
    }

    // Acceso seguro a inscripciones a través de una versión de solo lectura (inmutable) de la lista listadoInscripciones.
    public List<Inscripcion> getListadoInscripciones() {
        return Collections.unmodifiableList(listadoInscripciones);
    }

    //Se realizan los métodos agregar Inscripcion y eliminar con la finalidad de modificar el listado de inscriopciones en caso
    //De qué un participante cancele alguna inscripción. Debe destacarse que es un evento que se llama cada vez que un participante agrega o cancela una isncripcion. 
    public void agregarInscripcion(Inscripcion inscripcion) {
        if (inscripcion != null && !listadoInscripciones.contains(inscripcion)) {
            listadoInscripciones.add(inscripcion);
        }
    }

    public void eliminarInscripcion(Inscripcion inscripcion) {
        listadoInscripciones.remove(inscripcion);
    }

    @Override
    public String toString() {
        return "Evento{"
                + "id=" + id
                + ", nombre='" + nombre + '\''
                + ", fecha=" + fecha
                + ", horaInicio=" + horaInicio
                + ", duracion=" + duracion
                + ", estado=" + estado
                + ", modalidad=" + modalidad
                + ", ubicacion=" + ubicacion
                + ", categoria=" + categoria
                + ", organizador=" + organizador
                + ", inscripciones=" + listadoInscripciones.size()
                + '}';
    }
}
