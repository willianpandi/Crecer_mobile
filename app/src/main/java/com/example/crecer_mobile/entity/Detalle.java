package com.example.crecer_mobile.entity;

public class Detalle {
    private int cedula;
    private int cuenta;
    private float saldos;

    public Detalle(int cedula, int cuenta, float saldos) {
        this.cedula = cedula;
        this.cuenta = cuenta;
        this.saldos = saldos;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public int getCuenta() {
        return cuenta;
    }

    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }

    public float getSaldos() {
        return saldos;
    }

    public void setSaldos(float saldos) {
        this.saldos = saldos;
    }
}
