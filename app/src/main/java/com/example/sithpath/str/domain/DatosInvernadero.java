package com.example.sithpath.str.domain;

import com.example.sithpath.str.io.model.JsonKeys;
import com.google.gson.annotations.SerializedName;
/**
 * Created by sithpath on 2/01/16.
 */
public class DatosInvernadero {
    @SerializedName(JsonKeys.TEMPERATURA)
    private String temperatura;
    @SerializedName(JsonKeys.FECHA)
    private String fecha;
    @SerializedName(JsonKeys.HORA)
    private String hora;

    public String getHora(){ return hora;}
    public String getTemperatura() {return temperatura;}
    public String getFecha() {return fecha;}
}
