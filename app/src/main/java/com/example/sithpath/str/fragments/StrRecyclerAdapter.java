package com.example.sithpath.str.fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sithpath.str.R;
import com.example.sithpath.str.domain.DatosInvernadero;

import java.util.ArrayList;

/**
 * Created by Cruz on 03/01/2016.
 */
public class StrRecyclerAdapter extends RecyclerView.Adapter<StrRecyclerAdapter.StrRecyclerViewHolder>{
    int position;
    Context context;
    ArrayList<DatosInvernadero> datosInvernaderoArrayList;
    public StrRecyclerAdapter(Context context){
        this.context = context;
        datosInvernaderoArrayList = new ArrayList<DatosInvernadero>();
    }

    @Override
    public StrRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_temp_data,parent,false);
        return new StrRecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StrRecyclerViewHolder holder, int position) {
        DatosInvernadero datosInvernadero = datosInvernaderoArrayList.get(position);
        holder.setTemperatura(datosInvernadero.getTemperatura());
        holder.setFecha(datosInvernadero.getFecha());
        holder.setHora(datosInvernadero.getHora());
    }

    @Override
    public int getItemCount() {
        return datosInvernaderoArrayList.size();
    }
    public void addAll(@NonNull ArrayList<DatosInvernadero> datosInvernaderos){
        if(datosInvernaderos == null){
            throw new NullPointerException("No puede ser nullo el arreglo");
        }
        this.datosInvernaderoArrayList.clear();
        this.datosInvernaderoArrayList.addAll(datosInvernaderos);
        notifyDataSetChanged();
    }
    public class StrRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView hora, fecha, temperatura;
        public StrRecyclerViewHolder(View itemView){
            super(itemView);
            hora = (TextView)itemView.findViewById(R.id.textview_item_hora);
            fecha = (TextView)itemView.findViewById(R.id.textview_item_fecha);
            temperatura =(TextView) itemView.findViewById(R.id.textview_item_temperatura);
        }
        public void setHora(String hora){ this.hora.setText(hora); }
        public void setFecha(String fecha){this.fecha.setText(fecha);}
        public void setTemperatura(String temperatura){this.temperatura.setText(temperatura);}

        @Override
        public void onClick(View v) {
            Log.i("ViewHolder Adapter Position :) ",String.valueOf(getLayoutPosition()) );
        }
    }
}