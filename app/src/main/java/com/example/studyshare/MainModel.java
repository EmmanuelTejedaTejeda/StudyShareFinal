package com.example.studyshare;

public class MainModel {
    String descripcion;
    String imagen;
    String nombre;
    String tipo;

    public MainModel() {
    }

    public MainModel(String descripcion, String imagen, String nombre, String tipo) {
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
