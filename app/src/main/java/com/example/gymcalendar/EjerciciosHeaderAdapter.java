package com.example.gymcalendar;

import android.content.Context;
import android.telephony.ims.ImsMmTelManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EjerciciosHeaderAdapter extends RecyclerView.Adapter<EjerciciosHeaderAdapter.MyViewHolder> {

    private Context context;
    private List<Ejercicio> ejercicios;

    public EjerciciosHeaderAdapter(Context context,List<Ejercicio> ejercicios){
        this.context = context;
        this.ejercicios = ejercicios;
    }
    @NonNull
    @Override
    public EjerciciosHeaderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ejercicios_header,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EjerciciosHeaderAdapter.MyViewHolder holder, int position) {
        holder.tEjercicio.setText(String.valueOf(ejercicios.get(position).getEjercicio()));
        holder.tPeso.setText(String.valueOf(ejercicios.get(position).getPeso()));
        holder.tRepeticiones.setText(String.valueOf(ejercicios.get(position).getNumRepeticiones()));
        holder.tSeries.setText(String.valueOf(ejercicios.get(position).getNumSeries()));
    }

    @Override
    public int getItemCount() {
        return ejercicios.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tEjercicio, tPeso, tSeries, tRepeticiones;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tEjercicio = itemView.findViewById(R.id.tEjercicio);
            tPeso = itemView.findViewById(R.id.tPeso);
            tRepeticiones = itemView.findViewById(R.id.tRepeticiones);
            tSeries = itemView.findViewById(R.id.tSeries);
        }
    }
}
