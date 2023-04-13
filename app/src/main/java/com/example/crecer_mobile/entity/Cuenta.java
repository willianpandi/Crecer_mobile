package com.example.crecer_mobile.entity;

public class Cuenta {
    private int id;
    private int n_cuenta;
    private String nombre;
    private float saldo;

    public Cuenta(int id, int n_cuenta, String nombre, float saldo) {
        this.id = id;
        this.n_cuenta = n_cuenta;
        this.nombre = nombre;
        this.saldo = saldo;
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

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
