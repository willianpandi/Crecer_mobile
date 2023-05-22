package com.example.crecer_mobile.entity;

public class Cuenta_inicio {
    private int id;
    private int n_cuenta;
    private String nombre;
    private String email;

    public Cuenta_inicio(int id, int n_cuenta, String nombre, String email) {
        this.id = id;
        this.n_cuenta = n_cuenta;
        this.nombre = nombre;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
