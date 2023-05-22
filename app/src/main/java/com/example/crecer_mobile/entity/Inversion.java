package com.example.crecer_mobile.entity;

public class Inversion {
    private int cuenta;
    private int cedula;
    private String fecha;
    private float deposito;

    public Inversion(int cuenta, int cedula, String fecha, float deposito) {
        this.cuenta = cuenta;
        this.cedula = cedula;
        this.fecha = fecha;
        this.deposito = deposito;
    }

    public int getCuenta() {
        return cuenta;
    }

    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getDeposito() {
        return deposito;
    }

    public void setDeposito(float deposito) {
        this.deposito = deposito;
    }
}
