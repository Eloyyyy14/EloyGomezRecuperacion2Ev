package com.example.eloyyyyyyy.eloygomezrecuperacion2ev;

/**
 * Created by Eloyyyyyyy on 01/03/2018.
 */

public class Localidad {
    String nombre, latitud, longitud, codigoPais;

    public Localidad() {
    }

    public Localidad(String nombre, String latitud, String longitud, String codigoPais) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.codigoPais = codigoPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }
}
