package com.example.crudCelular.entity;

public class celuar {


    public  int nit;
    public String marca;
    public float tamano;

    public String capacidad;

    public float precio;


    public celuar(int nit, String marca, float tamano, String capacidad, float precio) {
        this.nit = nit;
        this.marca = marca;
        this.tamano = tamano;
        this.capacidad = capacidad;
        this.precio = precio;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getTamano() {
        return tamano;
    }

    public void setTamano(float tamano) {
        this.tamano = tamano;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "celuar{" +
                "nit =" +nit +
                ", marca='" + marca + '\'' +
                ", tamano=" + tamano +
                ", capacidad='" + capacidad + '\'' +
                ", precio=" + precio +
                '}';
    }
}
