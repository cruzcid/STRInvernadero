package com.example.sithpath.str.domain;

import com.google.gson.annotations.SerializedName;
/**
 * Created by sithpath on 2/01/16.
 */
public class DatosInvernadero {
    @SerializedName("temperatura")
    private String temperatura;


    @SerializedName("fecha")
    private String fecha;

    @SerializedName("hora")
    private String hora;

    public String getHora(){ return hora;}
    public String getTemperatura() {return temperatura;}
    public String getFecha() {return fecha;}


}
