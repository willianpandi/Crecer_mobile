package com.example.crecer_mobile.entity;

import java.util.Date;

public class Ahorro {
    private int cuenta;
    private String detalle;
    private String fecha;
    private float deposito;

    public Ahorro(int cuenta, String detalle, String fecha, float deposito) {
        this.cuenta = cuenta;
        this.detalle = detalle;
        this.fecha = fecha;
        this.deposito = deposito;
    }

    public int getCuenta() {
        return cuenta;
    }

    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public float getDeposito() {
        return deposito;
    }

    public void setDeposito(float deposito) {
        this.deposito = deposito;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
