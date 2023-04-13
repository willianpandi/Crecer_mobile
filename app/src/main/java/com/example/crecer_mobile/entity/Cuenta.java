package com.example.crecer_mobile.entity;

public class Cuenta {
    private int id;
    private String descripcion;
    private float precio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Cuenta(int id, String descripcion, float precio) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
    }
}
