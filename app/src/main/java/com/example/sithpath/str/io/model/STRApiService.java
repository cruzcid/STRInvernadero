package com.example.sithpath.str.io.model;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Cruz on 03/01/2016.
 */
public interface STRApiService {
    @FormUrlEncoded
    @GET(ApiConstants.REGISTRO_TEMPERATURA)
    void getRegistroTemperatura(Callback<RegTemperaturaResponse> cb);

    @FormUrlEncoded
    @POST(ApiConstants.REGISTRO_TEMPERATURA)
    void postRegistroTemperatura(@Field("fecha")String fecha,
                                 Callback<RegTemperaturaResponse> cb);
}
