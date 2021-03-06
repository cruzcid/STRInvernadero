package com.example.sithpath.str.io.model;

import com.example.sithpath.str.domain.DatosInvernadero;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
/**
 * Created by sithpath on 2/01/16.
 */
public class RegTemperaturaResponse {
    @SerializedName(JsonKeys.DATOS)
    ArrayList<DatosInvernadero> datosInvernadero;

    @SerializedName(JsonKeys.DATAEXIST)
    String dataexist;

    public ArrayList<DatosInvernadero> getDatosInvernadero(){ return datosInvernadero;}
    public String getDataexist(){return dataexist;}
}
