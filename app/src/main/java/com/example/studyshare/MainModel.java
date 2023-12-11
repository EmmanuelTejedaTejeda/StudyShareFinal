package com.example.studyshare;

public class MainModel {
    String descripcion;
    String imagen;
    String nombre;
    String archivoId;

    public MainModel() {
    }

    public MainModel(String descripcion, String imagen, String nombre, String archivoId) {
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.nombre = nombre;
        this.archivoId = archivoId;
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

    public String getArchivoId() {
        return archivoId;
    }

    public void setArchivoId(String archivoId) {
        this.archivoId = archivoId;
    }
}
