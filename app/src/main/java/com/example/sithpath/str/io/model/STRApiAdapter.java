package com.example.sithpath.str.io.model;

import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Created by Cruz on 03/01/2016.
 */
public class STRApiAdapter {
    private static STRApiService API_SERVICE;

    //Patron del Singleton
    public static STRApiService getApiService(){
        //Si no ha sido creado lo crea
        if(API_SERVICE == null){
            RestAdapter adapter = new RestAdapter.Builder()
                    .setEndpoint(ApiConstants.SCADA_BASE_URL)
                    .setLogLevel(RestAdapter.LogLevel.BASIC)
                    .build();
            API_SERVICE = adapter.create(STRApiService.class);
        }
        return API_SERVICE;
    }
    /*
    public static void getRegistroTemperatura(){
        getApiService().getRegistroTemperatura(new Callback<RegTemperaturaResponse>() {
            @Override
            public void success(RegTemperaturaResponse regTemperaturaResponse, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }*/
    public void postRegistroTemperatura(String fecha, Callback<RegTemperaturaResponse> cb){
        getApiService().postRegistroTemperatura(fecha, cb);
    }
}
