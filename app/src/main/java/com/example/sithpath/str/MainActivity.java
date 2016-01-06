package com.example.sithpath.str;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sithpath.str.domain.DatosInvernadero;
import com.example.sithpath.str.domain.Fecha;
import com.example.sithpath.str.fragments.StrRecyclerAdapter;
import com.example.sithpath.str.io.model.RegTemperaturaResponse;
import com.example.sithpath.str.io.model.STRApiAdapter;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    private StrRecyclerAdapter strRecyclerAdapter;
    private RecyclerView mDatosInvernaderoList;
    private ArrayList<DatosInvernadero> datosInvernadero;
    private String body;
    //private STRApiAdapter strAdapter;
    private Fecha fecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fecha = new Fecha();

        strRecyclerAdapter = new StrRecyclerAdapter(this);
        mDatosInvernaderoList = (RecyclerView) findViewById(R.id.recycler_datos_temperatura);
        setupDatosInvernaderoList();
        datosInvernadero = new ArrayList<>();
        /*
        datosInvernadero.add(new DatosInvernadero("2001-11-09", "23:32", "34.5"));
        datosInvernadero.add(new DatosInvernadero("2001-11-10","23:33","33.2"));
        datosInvernadero.add(new DatosInvernadero("2001-11-11","23:34","31.7"));
        */
        ImageView refreshImg = (ImageView) findViewById(R.id.image_view_refresh);
        refreshImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Actualizando datos", Toast.LENGTH_SHORT).show();
                datosInvernadero.clear();
                STRApiAdapter.postRegistroTemperatura("ho",new Callback<RegTemperaturaResponse>() {
                    @Override
                    public void success(RegTemperaturaResponse regTemperaturaResponse, Response response) {
                        if (regTemperaturaResponse.getDataexist().equals("true")) {
                            strRecyclerAdapter.addAll(regTemperaturaResponse.getDatosInvernadero());
                            datosInvernadero.addAll(regTemperaturaResponse.getDatosInvernadero());
                            Toast.makeText(MainActivity.this, "Datos Actualizados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Sin datos a actualizar", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                        Log.i("retrofit checa", error.getMessage());
                    }
                });
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!datosInvernadero.isEmpty()){

                    body = "Temperatura del invernadero\n";
                    for (DatosInvernadero dato : datosInvernadero) {
                        body += dato.getTemperatura()+" "+dato.getHora()+" "+dato.getFecha()+"\n";
                    }
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    //intent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Resgistros de temperatura al "+fecha.getYearMonthDay());
                    intent.putExtra(Intent.EXTRA_TEXT, body);
                    startActivity(Intent.createChooser(intent, "Send Email"));

                }
                else{
                    Snackbar.make(view, "Consulte datos por favor", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
        //Activa RecyclerView
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void setupDatosInvernaderoList(){
        mDatosInvernaderoList.setLayoutManager(new LinearLayoutManager(this));
        mDatosInvernaderoList.setAdapter(strRecyclerAdapter);
    }
}
