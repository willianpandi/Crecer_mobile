package com.example.crecer_mobile.entity;

public class Cuenta {
    private int id;
    private int n_cuenta;
    private String nombre;
    private String correo;

    public Cuenta(int id, int n_cuenta, String nombre, String correo) {
        this.id = id;
        this.n_cuenta = n_cuenta;
        this.nombre = nombre;
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getN_cuenta() {
        return n_cuenta;
    }

    public void setN_cuenta(int n_cuenta) {
        this.n_cuenta = n_cuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo(){
        return correo;
    }

    public  void setCorreo (String correo) {
        this.correo = correo;
    }
}
