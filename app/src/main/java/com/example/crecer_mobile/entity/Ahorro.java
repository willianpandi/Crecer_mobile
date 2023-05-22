package com.example.crecer_mobile.entity;

import java.util.Date;

public class Ahorro {
    private int cuenta;
    private String detalle;
    private float deposito;

    public Ahorro(int cuenta, String detalle, float deposito) {
        this.cuenta = cuenta;
        this.detalle = detalle;
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
}
